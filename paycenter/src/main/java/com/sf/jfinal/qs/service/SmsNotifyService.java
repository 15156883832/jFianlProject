package com.sf.jfinal.qs.service;

import com.jfinal.aop.Before;
import com.jfinal.log.Log;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.sf.jfinal.qs.Constants;
import com.sf.jfinal.qs.core.sms.PaymentNotifyService;
import com.sf.jfinal.qs.core.sms.SmsPaymentNotifyJob;
import com.sf.jfinal.qs.model.CrmGoodsPlatformOrder;
import com.sf.jfinal.qs.model.CrmSite;
import org.apache.commons.lang.StringUtils;

import java.util.Date;
import java.util.Map;

public class SmsNotifyService {

    private static final Log logger = Log.getLog(SmsNotifyService.class);

    @Before(Tx.class)
    public void handleSmsNotify(String outTradeNo, Map<String, String> parasMap, boolean isPaySuccess) {
        logger.error(String.format("check sign[%s] passed", parasMap.get("out_trade_no")));
        if (org.apache.commons.lang.StringUtils.isNotBlank(outTradeNo) && isPaySuccess) {
            CrmGoodsPlatformOrder platformOrder = CrmGoodsPlatformOrder.dao.findByTradeNo(outTradeNo);
            if (platformOrder != null && !Constants.PAY_STATUS_PAIED.equals(platformOrder.getPayStatus())) {
                logger.error(String.format("sms notify handles: %s", platformOrder));
                Date now = new Date();
                platformOrder.setStatus(Constants.ORDER_STATUS_YWC); // 已完成
                platformOrder.setPayStatus(Constants.PAY_STATUS_PAIED);
                platformOrder.setPaymentTime(now);
                platformOrder.setFinishTime(now);
                platformOrder.setSendgoodTime(now);
                platformOrder.setConfirmTime(now);
                platformOrder.update();

                String siteId = platformOrder.getSiteId();
                if (StringUtils.isBlank(siteId)) {
                    String err = String.format("site id is blank,out_trade_no=%s", outTradeNo);
                    throw new IllegalStateException(err);
                }

                CrmSite site = CrmSite.dao.findById(siteId);
                if (site == null) {
                    String err = String.format("site with id[%s] not found,out_trade_no=%s", siteId, outTradeNo);
                    throw new IllegalStateException(err);
                }

                long quantity = platformOrder.getPurchaseNum().longValue();
                logger.error(String.format("the quantity of sms is:%d", quantity));
                Db.update("update crm_site set sms_available_amount=sms_available_amount+" + quantity + ",update_time=? where id=?", new Date(), siteId);

                try {
                    PaymentNotifyService.submitWork(new SmsPaymentNotifyJob(platformOrder.getCustomerName(), platformOrder.getCustomerContact(), platformOrder.getPurchaseNum().intValue()));
                } catch (Exception ex) {
                    logger.error("sms notify failed", ex);
                }
            } else {
                if (platformOrder == null) {
                    logger.error(String.format("sms platform order not found, out trade no is[%s]", outTradeNo));
                }
            }
        }
    }
}
