package com.sf.jfinal.qs.service;

import com.jfinal.aop.Before;
import com.jfinal.log.Log;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.sf.jfinal.qs.Constants;
import com.sf.jfinal.qs.core.sms.ChaZuoPaymentNotifyJob;
import com.sf.jfinal.qs.core.sms.PaymentNotifyService;
import com.sf.jfinal.qs.model.CrmGoodsPlatformTransferOrder;
import org.apache.commons.lang.StringUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class NanDaoNotifyService {

    private static final Log logger = Log.getLog(NanDaoNotifyService.class);

    @Before(Tx.class)
    public void handleNanDaoNotify(String outTradeNo, Map<String, String> parasMap, boolean isPaySuccess) {
        logger.error(String.format("check sign[%s] passed", parasMap.get("out_trade_no")));
        if (StringUtils.isNotBlank(outTradeNo) && isPaySuccess) {
            List<CrmGoodsPlatformTransferOrder> orders = CrmGoodsPlatformTransferOrder.dao.findByTradeNo(outTradeNo);
            if (orders.size() > 0 && Constants.PAY_STATUS_PAIED.equals(orders.get(0).getPayStatus())) {
                return;
            }

            String customerName = "";
            String buyerMobile = "";
            BigDecimal totalPurchaseNum = BigDecimal.valueOf(0);
            for (CrmGoodsPlatformTransferOrder order : orders) {
                order.setPayStatus("1");
                order.setPaymentTime(new Date());
                customerName = order.getCustomerName();
                totalPurchaseNum = totalPurchaseNum.add(order.getPurchaseNum());
                buyerMobile = order.getCustomerContact();
                order.update();
            }
            try {
                PaymentNotifyService.submitWork(new ChaZuoPaymentNotifyJob(customerName, buyerMobile, totalPurchaseNum.intValue()));
            } catch (Exception ex) {
                logger.error("nandao notify failed", ex);
            }
        }
    }
}
