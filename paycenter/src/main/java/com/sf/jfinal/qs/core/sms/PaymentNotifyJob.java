package com.sf.jfinal.qs.core.sms;

import com.jfinal.kit.StrKit;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Sms Notify specified User when payed.
 */
public class PaymentNotifyJob implements Runnable {

    private static Logger logger = Logger.getLogger(PaymentNotifyJob.class);
    public static final String MSG_TMPL = "【思方科技】尊敬的用户，您成功购买了%s，%s，感谢您的支持！";
    private Pattern mobilePatt = Pattern.compile("^1\\d{10}$");
    /**
     * 短信通知人
     */
    private List<String> interests;
    private String interestsMsg;
    private String buyerMsg;
    private String buyerMobile;

    @Override
    public void run() {
        try {
            String joinedInterests = StringUtils.join(interests, ",");
            SMSUtils.sendMsg(joinedInterests, interestsMsg, "", "");
            if (isMobile(buyerMobile)) {
                SMSUtils.sendMsg(buyerMobile.trim(), buyerMsg, "", "");
            }
        } catch (Exception e) {
            logger.error("sms notify error:" + e.getMessage());
            logger.error("sms notify process error", e);
        }
    }

    public void init(List<String> notifierList, String msg, String buyerMobile, String buyerMsg) {
        this.interests = new ArrayList<>();
        for (String mobile : notifierList) {
            if (isMobile(mobile)) {
                interests.add(mobile.trim());
            }
        }
        this.interestsMsg = msg;
        this.buyerMsg = buyerMsg;
        this.buyerMobile = buyerMobile;
    }

    private boolean isMobile(String mobile) {
        return !StrKit.isBlank(mobile) && mobilePatt.matcher(mobile.trim()).matches();
    }
}
