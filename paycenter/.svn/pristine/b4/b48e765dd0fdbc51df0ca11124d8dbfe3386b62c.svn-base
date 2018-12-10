package com.sf.jfinal.qs.service;

import com.jfinal.aop.Before;
import com.jfinal.log.Log;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.sf.jfinal.qs.Constants;
import com.sf.jfinal.qs.core.sms.PaymentNotifyService;
import com.sf.jfinal.qs.core.sms.QRPaymentNotifyJob;
import com.sf.jfinal.qs.model.CrmGoodsPlatform;
import com.sf.jfinal.qs.model.CrmGoodsPlatformOrder;

import java.util.Date;
import java.util.Map;

public class CodeNotifyService {

    private static final Log logger = Log.getLog(CodeNotifyService.class);

    @Before(Tx.class)
    public void handleCodeNotify(String outTradeNo, Map<String, String> parasMap, boolean isPaySuccess) {
        logger.error(String.format("check sign[%s] passed", parasMap.get("out_trade_no")));
        if (org.apache.commons.lang.StringUtils.isNotBlank(outTradeNo) && isPaySuccess) {
            CrmGoodsPlatformOrder platformOrder = CrmGoodsPlatformOrder.dao.findByTradeNo(outTradeNo);
            if (platformOrder != null && !Constants.PAY_STATUS_PAIED.equals(platformOrder.getPayStatus())) {
                logger.error(String.format("qr notify handles: %s", platformOrder));
                boolean isAutoDistributionType = CrmGoodsPlatform.dao.isAutoDistributionType(platformOrder.getGoodId());
                platformOrder.setStatus(isAutoDistributionType ? Constants.ORDER_STATUS_DFH : Constants.ORDER_STATUS_DFP);
                platformOrder.setPayStatus(Constants.PAY_STATUS_PAIED);
                platformOrder.setPaymentTime(new Date());
                platformOrder.update();
                try {
                    PaymentNotifyService.submitWork(new QRPaymentNotifyJob(platformOrder.getCustomerName(), platformOrder.getCustomerContact(), platformOrder.getPurchaseNum().intValue()));
                } catch (Exception ex) {
                    logger.error("qr notify failed", ex);
                }
            } else {
                if (platformOrder == null) {
                    logger.error(String.format("qr notify handle error, platform order with trade no[%s] not found", outTradeNo));
                }
            }
        }
    }
}
