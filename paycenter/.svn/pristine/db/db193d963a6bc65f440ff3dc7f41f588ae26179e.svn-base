package com.sf.jfinal.qs.core.tools;

import java.util.regex.Pattern;

public class ValidateKit {

    private static final String EMAIL_PATTERN = "\\b(^['_A-Za-z0-9-]+(\\.['_A-Za-z0-9-]+)*@([A-Za-z0-9-])+(\\.[A-Za-z0-9-]+)*((\\.[A-Za-z0-9]{2,})|(\\.[A-Za-z0-9]{2,}\\.[A-Za-z0-9]{2,}))$)\\b";
    private static final String MOBILE_PATTERN = "\\b(1[35789]\\d{9})\\b";
    private static final String PHONE_PATTERN = "\\b((1[35789]\\d{9})|(0(\\d{2,3})-\\d{6,9}))\\b";

    public static boolean isEmail(String email) {
        return StrKit.isNotBlank(email) && Pattern.compile(EMAIL_PATTERN).matcher(email).matches();
    }

    public static boolean isMobile(String mobile) {
        return StrKit.isNotBlank(mobile) && Pattern.compile(MOBILE_PATTERN).matcher(mobile).matches();
    }

    public static boolean isPhone(String phone) {
        return StrKit.isNotBlank(phone) && Pattern.compile(PHONE_PATTERN).matcher(phone).matches();
    }

    /**
     * 手机号和电话号码都能通过验证。
     */
    public static boolean isPhoneNumber(String number) {
        return isMobile(number) || isPhone(number);
    }

}
