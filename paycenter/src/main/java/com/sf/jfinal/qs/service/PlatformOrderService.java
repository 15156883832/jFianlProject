package com.sf.jfinal.qs.service;

import com.jfinal.aop.Before;
import com.jfinal.kit.StrKit;
import com.jfinal.log.Log;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.sf.jfinal.qs.Constants;
import com.sf.jfinal.qs.model.CrmGoodsPlatform;
import com.sf.jfinal.qs.model.CrmGoodsPlatformOrder;
import com.sf.jfinal.qs.model.CrmGoodsSiteselfOrder;
import net.jojowo.sqlit.utils.SqlKit;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

public class PlatformOrderService {

    private static final Log logger = Log.getLog(PlatformOrderService.class);

    /**
     * 收款：指信息员确认收了服务工程师向用户收的钱。之前的逻辑是先收款，然后才能向平台下单（如果公司库存不足的话）或者出库（公司库存充足），
     * 现在的逻辑是收款和[下单/出库]是并列的，也即可以先[下单/出库]或者先收款。
     */
    @Before(Tx.class)
    public void handlePlatformOrderNotify(String outTradeNo, Map<String, String> parasMap, boolean isPaySuccess) {
        logger.error(String.format("check sign[%s] passed", parasMap.get("out_trade_no")));
        if (org.apache.commons.lang.StringUtils.isNotBlank(outTradeNo) && isPaySuccess) {
            CrmGoodsPlatformOrder platformOrder = CrmGoodsPlatformOrder.dao.findByTradeNo(outTradeNo);
            if (platformOrder != null && !Constants.PAY_STATUS_PAIED.equals(platformOrder.getPayStatus())) {
                logger.error(String.format("platform order notify handles: %s", platformOrder));
                String pid = platformOrder.getId();
                boolean isAutoDistributionType = CrmGoodsPlatform.dao.isAutoDistributionType(platformOrder.getGoodId());
                logger.error(String.format("platform order with trade no[%s] callback, auto.disp=%b", outTradeNo, isAutoDistributionType));

                // 更新平台订单状态
                platformOrder.setStatus(isAutoDistributionType ? Constants.ORDER_STATUS_DFH : Constants.ORDER_STATUS_DFP);
                Date now = new Date();
                platformOrder.setPayStatus(Constants.PAY_STATUS_PAIED);
                platformOrder.setPaymentTime(now);
                platformOrder.update();

                // 根据平台订单找到网点订单并更新网点订单
                String siteSelfOrderId = platformOrder.getSiteselfOrderId();
                CrmGoodsSiteselfOrder siteSelfOrder = CrmGoodsSiteselfOrder.dao.findById(siteSelfOrderId);
                if ("3".equals(siteSelfOrder.getStatus())) {
                    throw new IllegalStateException(String.format("site self order[%s] already handled", siteSelfOrder.getId()));
                }

                // 收款是指工程师上交向用户收的钱。
                if ("1".equals(siteSelfOrder.getStatus())) {//原本状态为1：待收款待下单，下单后变成状态4，待收款已下单
                    siteSelfOrder.setStatus("4");
                } else {
                    siteSelfOrder.setStatus("3");
                }
                siteSelfOrder.setOutstockType("2");
                siteSelfOrder.setOutstockTime(new Date()); // 其实是下单时间
                siteSelfOrder.update();

                BigDecimal purchaseNum = platformOrder.getPurchaseNum();
                if (purchaseNum == null) {
                    throw new IllegalStateException(String.format("platform order[%s] with purchase num is null", pid));
                }

                logger.error(String.format("platform order[%s] with purchase num[%s]", pid, purchaseNum));
                String siteSelfGoodId = siteSelfOrder.getGoodId();
                if (StrKit.isBlank(siteSelfGoodId)) {
                    throw new IllegalStateException("site self good is is not null");
                }

                // 更新网点销售数量
                SqlKit kit = new SqlKit()
                        .append("update crm_goods_siteself")
                        .append("set `sales`=`sales`+" + purchaseNum)
                        .append("where `id`=?");
                Db.update(kit.toString(), siteSelfGoodId);
            } else {
                if (platformOrder == null) {
                    logger.error(String.format("platform order notify handle error, platform order with trade no[%s] not found", outTradeNo));
                }
            }
        }
    }
}
