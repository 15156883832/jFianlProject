package com.sf.jfinal.qs.model;

import com.sf.jfinal.qs.model.base.BaseCrmSite;
import org.apache.commons.lang3.time.DateUtils;

import java.util.Date;

/**
 * Generated by JFinal.
 */
@SuppressWarnings("serial")
public class CrmSite extends BaseCrmSite<CrmSite> {
    public static final CrmSite dao = new CrmSite();

    public CrmSite findById(String sid) {
        return dao.findFirst("select * from crm_site where id=? and `status`='0'", sid);
    }

    public CrmSite doXuFei(String sid, int months) {
        CrmSite sharingSite = dao.findById(sid);
        sharingSite.setUpdateTime(new Date());
        sharingSite.setDueTime(doXuFei(sharingSite.getDueTime(), months));
        sharingSite.update();
        return sharingSite;
    }

    private static Date doXuFei(Date dueTime, int months) {
        Date now = new Date();
        if (dueTime == null || dueTime.before(now)) {
            dueTime = new Date();
        }
        return DateUtils.addMonths(dueTime, months);
    }
}