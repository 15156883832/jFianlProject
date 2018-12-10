package com.sf.jfinal.qs.service;

import com.jfinal.aop.Before;
import com.jfinal.log.Log;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.sf.jfinal.qs.Constants;
import com.sf.jfinal.qs.core.sms.ChaZuoPaymentNotifyJob;
import com.sf.jfinal.qs.core.sms.HaoZePaymentNotifyJob;
import com.sf.jfinal.qs.core.sms.PaymentNotifyService;
import com.sf.jfinal.qs.model.CrmGoodsPlatform;
import com.sf.jfinal.qs.model.CrmGoodsPlatformOrder;
import org.apache.commons.lang.StringUtils;

import java.util.Date;
import java.util.Map;

public class COPNotifyService {

    private static final Log logger = Log.getLog(COPNotifyService.class);

    @Before(Tx.class)
    public void handleCOPNotify(String outTradeNo, Map<String, String> parasMap, boolean isPaySuccess) {
        logger.error(String.format("check sign[%s] passed", parasMap.get("out_trade_no")));
        if (StringUtils.isNotBlank(outTradeNo) && isPaySuccess) {
            CrmGoodsPlatformOrder platformOrder = CrmGoodsPlatformOrder.dao.findByTradeNo(outTradeNo);
            if (platformOrder != null && !Constants.PAY_STATUS_PAIED.equals(platformOrder.getPayStatus())) {
                logger.error(String.format("COP notify handles: %s", platformOrder));
                boolean isAutoDistributionType = CrmGoodsPlatform.dao.isAutoDistributionType(platformOrder.getGoodId());
                platformOrder.setStatus(isAutoDistributionType ? Constants.ORDER_STATUS_DFH : Constants.ORDER_STATUS_DFP);

                Date now = new Date();
                platformOrder.setPayStatus(Constants.PAY_STATUS_PAIED);
                platformOrder.setPaymentTime(now);
                platformOrder.update();

                try {
                    String goodName = platformOrder.getGoodName();
                    String buyerMobile = platformOrder.getCustomerContact();
                    int buyNum = platformOrder.getPurchaseNum().intValue();
                    String buyer = platformOrder.getCustomerName();
                    if ("浩泽家用反渗透直饮机".equals(goodName)) {
                        PaymentNotifyService.submitWork(new HaoZePaymentNotifyJob(buyer, buyerMobile, buyNum));
                    } else if ("漏电保护插头".equals(goodName)) {
                        PaymentNotifyService.submitWork(new ChaZuoPaymentNotifyJob(buyer, buyerMobile, buyNum));
                    }
                } catch (Exception ex) {
                    logger.error("sms notify failed", ex);
                }
            } else {
                if (platformOrder == null) {
                    logger.error(String.format("COP notify handle error, platform order with trade no[%s] not found", outTradeNo));
                }
            }
        }
    }
}
