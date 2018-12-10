package com.sf.jfinal.qs.validator;

import com.sf.jfinal.qs.core.tools.StrKit;
import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

public class SignupValidator extends Validator {
    protected void validate(Controller c) {
        validateRegex("user.username", "^[a-zA-Z]{1}[a-zA-Z0-9]{2,}$", "error.user.username", "用户名格式不正确");
        validateString("user.password", 6, 30, "error.user.password", "密码长度应在6-30位之间");
        validateEqualField("user.password", "passwordConfirm", "error.user.passwordConfirm", "两次密码不匹配");
        validateEmail("user.email", "error.user.email", "邮箱格式不正确");

        String mobile = StrKit.trimNull(c.getPara("user.mobile"));
        if (!mobile.matches("^\\d{11}$")) {
            addError("error.user.mobile", "手机号格式不正确");
        }

        String gender = c.getPara("user.gender");
        if (!"M".equals(gender) && !"F".equals(gender)) {
            addError("error.user.gender", "性别？");
        }
    }

    protected void handleError(Controller c) {
        c.render("signup.jsp");
    }
}
