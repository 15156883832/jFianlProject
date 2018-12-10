package com.sf.jfinal.qs.model;

import com.sf.jfinal.qs.core.tools.RandomKit;
import com.sf.jfinal.qs.model.base.BaseCrmGiftPack;
import org.apache.commons.lang.StringUtils;

import java.util.Date;

/**
 * Generated by JFinal.
 */
@SuppressWarnings("serial")
public class CrmGiftPack extends BaseCrmGiftPack<CrmGiftPack> {
    public static final CrmGiftPack dao = new CrmGiftPack();

    public void createVipGiftPack(int months, String forSite) {
        if (months <= 0) {
            throw new RuntimeException("月份不正确");
        }
        if (StringUtils.isBlank(forSite)) {
            throw new RuntimeException("invalid site id");
        }

        CrmGiftPack pack = new CrmGiftPack();
        pack.setId(RandomKit.UUID());
        pack.setIdc("00");
        pack.setName("思方VIP会员");
        pack.setNum(months);
        pack.setTakeSiteId(forSite);
        pack.setDescription(createVipGiftDescription(months));
        pack.setCreateTime(new Date());
        pack.save();
    }

    private String createVipGiftDescription(int months) {
        if (months >= 1 && months < 12) {
            return String.format("思方VIP会员%d个月有效期", months);
        } else if (months == 12) {
            return "思方VIP会员1年有效期";
        } else if (months == 24) {
            return "思方VIP会员2年有效期";
        }
        throw new RuntimeException("create vip desc error for invalid months: " + months);
    }
}
