package com.sf.jfinal.qs.core.validator;

import com.sf.jfinal.qs.core.tools.ValidateKit;
import com.jfinal.validate.Validator;

import java.util.Set;

/**
 * @author gaols
 */
public abstract class QSValidator extends Validator {
    /**
     * 验证指定字段的值是否为限定的列表中。
     *
     * @param field        field to be validated
     * @param constraints  a collection of constraint values
     * @param errorKey     error message key
     * @param errorMessage error message
     */
    protected void validateIn(String field, Set<String> constraints, String errorKey, String errorMessage) {
        String[] values = controller.getRequest().getParameterValues(field);
        if (values != null) {
            for (String value : values) {
                if (!constraints.contains(value)) {
                    addError(errorKey, errorMessage);
                    break;
                }
            }
        }
    }

    /**
     * 校验手机号码。
     *
     * @param field 手机号码对应的表单字段
     */
    protected void validateMobile(String field, String errorKey, String errorMessage) {
        String mobile = controller.getPara(field);
        if (!ValidateKit.isMobile(mobile)) {
            addError(errorKey, errorMessage);
        }
    }

    /**
     * 验证指定字段是否有值，通常用于多选验证。
     */
    protected void validateRequiredMulti(String field, String errorKey, String errorMessage) {
        String[] values = controller.getRequest().getParameterValues(field);
        if (values == null || values.length <= 0) {
            addError(errorKey, errorMessage);
        }
    }

    /**
     * 验证密码是否匹配。
     *
     * @param field1 密码字段
     * @param field2 密码确认字段
     */
    protected void validateConfirmation(String field1, String field2, String errorKey, String errorMessage) {
        validateEqualField(field1, field2, errorKey, errorMessage);
    }
}
