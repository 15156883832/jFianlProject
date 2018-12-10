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
 * 浩泽家用反渗透直饮机
 * 会员：尊敬的用户，您成功购买思方ERP会员，重新登陆体验会员功能，感谢您的支持！
 */
public class VipPaymentNotifyJob extends PaymentNotifyJob {
    // 13261321718，13966792733，18555111666
    public VipPaymentNotifyJob(String buyer, String buyerMobile) {
        List<String> interests = new ArrayList<>();
        interests.add("13966792733");
        interests.add("18555111666");
        interests.add("17756016668");
        interests.add("18019929617");
        interests.add("17356553634");
        interests.add("13637066851");
        interests.add("13866702670");
        interests.add("17356553634");
//        interests.add("13855660145");
        String msg = String.format(MSG_TMPL, "思方ERP会员", "重新登陆体验会员功能");
        init(interests, msg, buyerMobile, msg);
    }
}
