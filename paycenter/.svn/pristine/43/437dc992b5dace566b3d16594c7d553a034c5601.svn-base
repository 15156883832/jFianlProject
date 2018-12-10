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
 * //    public static final String MSG_TMPL = "【思方科技】@购买了@产品。";
 * 漏保：尊敬的用户，您成功购买15个漏电保护器，我们会尽快给您邮寄，感谢您的支持！
 */
public class DetergentPaymentNotifyJob extends PaymentNotifyJob {
    //17756016668，13966792733，18555111666
    public DetergentPaymentNotifyJob(String buyer, String buyerMobile, int buyNum) {
        List<String> interests = new ArrayList<>();
        interests.add("17756016668");
        interests.add("13966792733");
        interests.add("18555111666");
        String msg = String.format(MSG_TMPL, buyNum + "个" + "洁力士清洁剂", "我们会尽快给您邮寄");
        init(interests, msg, buyerMobile, msg);
    }
}
