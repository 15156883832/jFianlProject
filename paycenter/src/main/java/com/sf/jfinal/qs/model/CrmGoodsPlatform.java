package com.sf.jfinal.qs.model;

import com.sf.jfinal.qs.model.base.BaseCrmGoodsPlatform;

/**
 * Generated by JFinal.
 */
@SuppressWarnings("serial")
public class CrmGoodsPlatform extends BaseCrmGoodsPlatform<CrmGoodsPlatform> {

    public static final String DIS_TYPE_AUTO = "1";
    public static final String DIS_TYPE_MANUALLY = "2";

    public static final CrmGoodsPlatform dao = new CrmGoodsPlatform();

    public boolean isAutoDistributionType(String id) {
        CrmGoodsPlatform goodsPlatform = dao.findById(id);
        if (goodsPlatform == null) {
            throw new IllegalArgumentException(String.format("CrmGoodsPlatform with id[%s] not found", id));
        }

        return DIS_TYPE_AUTO.equals(goodsPlatform.getDistributionType());
    }
}
