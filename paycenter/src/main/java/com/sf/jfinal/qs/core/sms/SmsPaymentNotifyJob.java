package com.sf.jfinal.qs.core.sms;

import java.util.ArrayList;
import java.util.List;

/**
 * 思方会员：13966792733，18555111666，18919681981,17756016668
 * <p>
 * 浩泽净水：13261321718，13966792733，18555111666
 * <p>
 * 插座：17756016668，13966792733，18555111666
 * <p>
 * 弹屏：13966792733，18555111666
 * <p>
 * 短信：13966792733，18555111666
 * <p>
 * 短信：尊敬的用户，您成功购买10000条短信，重新登陆查看短信余额，感谢您的支持！
 */
public class SmsPaymentNotifyJob extends PaymentNotifyJob {
    // 13261321718，13966792733，18555111666
    public SmsPaymentNotifyJob(String buyer, String buyerMobile, int buyNum) {
        List<String> interests = new ArrayList<>();
        interests.add("13966792733");
        interests.add("18555111666");
        String msg = String.format(MSG_TMPL, buyNum + "条" + "短信", "重新登陆查看短信余额");
        init(interests, msg, buyerMobile, msg);
    }
}
