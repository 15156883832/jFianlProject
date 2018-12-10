package com.sf.jfinal.qs.service;

import com.jfinal.log.Log;
import com.sf.jfinal.qs.Constants;
import com.sf.jfinal.qs.core.sms.PaymentNotifyService;
import com.sf.jfinal.qs.core.sms.TPPaymentNotifyJob;
import com.sf.jfinal.qs.model.CrmGoodsPlatform;
import com.sf.jfinal.qs.model.CrmGoodsPlatformOrder;

import java.util.Date;
import java.util.Map;

public class TPNotifyService {

    private static final Log logger = Log.getLog(TPNotifyService.class);

    /**
     * 自动分配决定了两方面的内容：
     * 1.如果自动分配，那么付款成功后的状态为待发货，否则付款成功后状态为待分配确认；
     * 2.如果是自动分配，那么创建平台订单时，supplier_id 需要到 crm_goods_platform_supplier_rel 表中根据订单中 goods_id 找，否则为空。
     *
     * @param outTradeNo   业务订单号
     * @param parasMap     支付回调的参数集合
     * @param isPaySuccess 是否支付成功
     */
    public void handleTPNotify(String outTradeNo, Map<String, String> parasMap, boolean isPaySuccess) {
        logger.error(String.format("check sign[%s] passed", parasMap.get("out_trade_no")));
        if (org.apache.commons.lang.StringUtils.isNotBlank(outTradeNo) && isPaySuccess) {
            CrmGoodsPlatformOrder platformOrder = CrmGoodsPlatformOrder.dao.findByTradeNo(outTradeNo);
            if (platformOrder != null && !Constants.PAY_STATUS_PAIED.equals(platformOrder.getPayStatus())) {
                logger.error(String.format("tp notify handles: %s", platformOrder));
                boolean isAutoDistributionType = CrmGoodsPlatform.dao.isAutoDistributionType(platformOrder.getGoodId());
                platformOrder.setStatus(isAutoDistributionType ? Constants.ORDER_STATUS_DFH : Constants.ORDER_STATUS_DFP);

                platformOrder.setPayStatus(Constants.PAY_STATUS_PAIED);
                platformOrder.setPaymentTime(new Date());
                platformOrder.update();
                try {
                    PaymentNotifyService.submitWork(new TPPaymentNotifyJob(platformOrder.getCustomerName(), platformOrder.getCustomerContact(), platformOrder.getPurchaseNum().intValue()));
                } catch (Exception ex) {
                    logger.error("sms notify failed", ex);
                }
            } else {
                if (platformOrder == null) {
                    logger.error(String.format("tp notify handle error, platform order with trade no[%s] not found", outTradeNo));
                }
            }
        }
    }
}
