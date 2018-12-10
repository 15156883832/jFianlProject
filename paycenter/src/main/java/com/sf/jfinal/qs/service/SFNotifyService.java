package com.sf.jfinal.qs.service;

import com.gaols.unipay.utils.MD5;
import com.google.gson.Gson;
import com.jfinal.aop.Before;
import com.jfinal.log.Log;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.sf.jfinal.qs.Constants;
import com.sf.jfinal.qs.core.sms.PaymentNotifyService;
import com.sf.jfinal.qs.core.sms.VipPaymentNotifyJob;
import com.sf.jfinal.qs.core.tools.RandomKit;
import com.sf.jfinal.qs.model.*;
import net.jojowo.sqlit.utils.SqlKit;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.RandomStringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class SFNotifyService {
    private static final Log logger = Log.getLog(SFNotifyService.class);

    /**
     * 服务商登录系统后，开通或者续费的回调。服务商使用服务商的激活码注册的时候，就需要付费才能注册。
     */
    @Before(Tx.class)
    public void handleSFNotify(String outTradeNo, Map<String, String> parasMap, boolean isPaySuccess) {
        logger.error(String.format("check sign[%s] passed", parasMap.get("out_trade_no")));
        if (org.apache.commons.lang.StringUtils.isNotBlank(outTradeNo) && isPaySuccess) {
            CrmGoodsPlatformOrder platformOrder = CrmGoodsPlatformOrder.dao.findByTradeNo(outTradeNo);
            if (platformOrder != null && !Constants.PAY_STATUS_PAIED.equals(platformOrder.getPayStatus())) {
                logger.error(String.format("VIP notify handles: %s", platformOrder));
                Date now = new Date();
                platformOrder.setStatus(Constants.ORDER_STATUS_YWC); // 已完成
                platformOrder.setPayStatus(Constants.PAY_STATUS_PAIED);
                platformOrder.setPaymentTime(now);
                platformOrder.setFinishTime(now);
                platformOrder.setSendgoodTime(now);
                platformOrder.setConfirmTime(now);
                platformOrder.update();

                String siteId = platformOrder.getSiteId();
                if (StringUtils.isBlank(siteId)) {
                    String err = String.format("site id is blank,out_trade_no=%s", outTradeNo);
                    throw new IllegalStateException(err);
                }

                CrmSite site = CrmSite.dao.findById(siteId);
                if (site == null) {
                    String err = String.format("site with id[%s] not found,out_trade_no=%s", siteId, outTradeNo);
                    throw new IllegalStateException(err);
                }

                long month = platformOrder.getPurchaseNum().longValue(); // 　这里的purchase_num表示购买的月份数量
                logger.error(String.format("the month of SF is:%d", month));

                if (isVipNotExpired(site.getDueTime())) {
                    SqlKit kit = new SqlKit()
                            .append("UPDATE crm_site a")
                            .append(String.format("SET a.due_time=DATE_ADD(DATE_ADD(a.due_time,INTERVAL %d MONTH), INTERVAL -1 DAY),", month))
                            .append("a.update_time=now(),")
                            .append("a.status='0'")
                            .append("WHERE a.id=?");
                    Db.update(kit.toString(), siteId);
                } else {
                    // due_time is null or due_time is expired.
                    // but now, this site my be shared by another site or area manager code.
                    SqlKit kit = new SqlKit()
                            .append("UPDATE crm_site a")
                            .append(String.format("SET a.due_time=DATE_ADD(DATE_ADD(now(), INTERVAL %d MONTH), INTERVAL -1 DAY),", month))
                            .append("a.update_time=now(),")
                            .append("a.status='0'")
                            .append("WHERE a.id=?");
                    Db.update(kit.toString(), siteId);
                }

                try {
                    PaymentNotifyService.submitWork(new VipPaymentNotifyJob(platformOrder.getCustomerName(), platformOrder.getCustomerContact()));
                } catch (Exception ex) {
                    logger.error("sms notify failed", ex);
                }
            } else {
                if (platformOrder == null) {
                    String errmsg = String.format("SF VIP, platform order with trade no[%s] not found", outTradeNo);
                    logger.error(errmsg);
                    throw new IllegalStateException(errmsg);
                }

                if (Constants.PAY_STATUS_PAIED.equals(platformOrder.getStatus())) {
                    logger.error("SF VIP, DUP callback found");
                }
            }
        }
    }


    /**
     * 服务商注册过程中，使用服务商激活码，直接购买付款后的回调。-----这是以前的逻辑，现在可以直接使用服务商的激活码注册免费版本的用户。
     */
    @Before(Tx.class)
    public void handleSFNotify1(String outTradeNo, Map<String, String> parasMap, boolean isPaySuccess) {
        logger.error(String.format("check sign[%s] passed", parasMap.get("out_trade_no")));
        if (org.apache.commons.lang.StringUtils.isNotBlank(outTradeNo) && isPaySuccess) {
            CrmGoodsPlatformOrder platformOrder = CrmGoodsPlatformOrder.dao.findByTradeNo(outTradeNo);
            if (platformOrder != null && !Constants.PAY_STATUS_PAIED.equals(platformOrder.getPayStatus())) {
                logger.error(String.format("VIP notify handles: %s", platformOrder));
                Date now = new Date();
                long month = platformOrder.getPurchaseNum().longValue(); // 　这里的purchase_num表示购买的月份数量
                String userSiteMsg = platformOrder.getUserSiteMsg();// 缓存信息
                UserSiteMsg usm = new Gson().fromJson(userSiteMsg, UserSiteMsg.class);
                String shareCode = usm.getShareCode();
                Record rd = Db.findFirst("select a.* from crm_site a where a.share_code=? and a.status='0'", shareCode);
                User user = new User();
                CrmSite site = new CrmSite();
                user.setId(RandomKit.UUID());
                user.setLoginName(usm.getMobile());// 登录名
                user.setPassword(MD5.MD5(usm.getPassWord()));// 登陆密码
                user.setCreateDate(new Date());
                user.setUpdateTime(new Date());
                user.setUserType("2");// 网点
                user.setCreateType("2");
                user.setStatus("0");
                user.setMobile(usm.getMobile());
                user.save();

                site.setId(RandomKit.UUID());
                site.setName(usm.getName());
                site.setMobile(usm.getMobile());
                site.setStatus("0");// 审核中
                site.setUserId(user.getId());
                site.setLevel("0");
                site.setNumber(genUniqueNo());
                site.setCheckFlag("0");
                site.setUpdateTime(new Date());
                site.setCreateTime(new Date());
                site.setCity(usm.getCity());
                site.setArea(usm.getArea());
                site.setProvince(usm.getProvince());
                site.setAddress(usm.getAddress());
                site.setSmsAvailableAmount(0);
                site.setShareCode(genSiteShareCode());// 服务商随机生成的分享码
                site.setShareCodeSiteParentId(rd.getStr("id"));//购买时使用的分享码所属网点的ID
                if (StringUtils.isNotBlank(rd.getStr("area_manager_id"))) {
                    site.setAreaManagerId(rd.getStr("area_manager_id"));//区管id
                }
                site.save();
                Db.update("insert into sys_user_role (user_id,role_id) values ('" + user.getId() + "','3')");
                platformOrder.setStatus(Constants.ORDER_STATUS_YWC); // 已完成
                platformOrder.setPayStatus(Constants.PAY_STATUS_PAIED);
                platformOrder.setPaymentTime(now);
                platformOrder.setFinishTime(now);
                platformOrder.setSendgoodTime(now);
                platformOrder.setConfirmTime(now);
                platformOrder.setPlacingOrderBy(user.getId());
                platformOrder.setPlacingOrderTime(now);
                platformOrder.setSiteId(site.getId());
                platformOrder.update();
                Db.update("UPDATE crm_site a SET a.due_time=DATE_ADD(DATE_ADD(now(),INTERVAL " + month + " MONTH),INTERVAL -1 DAY),a.update_time=now(),a.status='0' WHERE a.id='" + site.getId() + "'");

                try {
                    PaymentNotifyService.submitWork(new VipPaymentNotifyJob(platformOrder.getCustomerName(), platformOrder.getCustomerContact()));
                    int buyVipMonths = platformOrder.getPurchaseNum().intValue();
                    int freeVipMonthsForSharingSite = calcVipFreeMonth(buyVipMonths); // 计算主动分享网点可获得的免费vip时长
                    CrmGiftPack.dao.createVipGiftPack(freeVipMonthsForSharingSite, site.getShareCodeSiteParentId());
                } catch (Exception ex) {
                    logger.error("sms notify failed", ex);
                }
            } else {
                logger.error(String.format("SF VIP, FOUND pay status is %s", parasMap.get("trade_status")));
            }
        }
    }

    private static String genUniqueNo() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        return sdf.format(new Date()) + RandomStringUtils.randomNumeric(4);
    }

    /**
     * 根据被分享的服务商购买的vip时长，计算主动分享网点应该赠予的月份数量。
     * @param sharingSiteBuyMonth 分享的服务商购买的vip时长
     * @return 主动分享网点享受的免费VIP时长。
     */
    private int calcVipFreeMonth(int sharingSiteBuyMonth) {
        if (sharingSiteBuyMonth >= 1 && sharingSiteBuyMonth < 6) {
            return 1;
        } else if (sharingSiteBuyMonth >= 6 && sharingSiteBuyMonth < 12) {
            return 2;
        } else if (sharingSiteBuyMonth >= 12) {
            return 3;
        }
        return 0;
    }

    private String genSiteShareCode() {
        Long count1, count2;
        String shareCode;
        do {
            shareCode = RandomStringUtils.randomAlphanumeric(4);// 服务商随机生成的分享码
            count1 = Db.queryLong("select count(1) from crm_area_manager a where a.status='0' and a.code=?", shareCode);// 区管码
            count2 = Db.queryLong("select count(1) from crm_site a where a.status='0' and a.share_code=?", shareCode);// 分享码
        } while (count1 > 1 || count2 > 1);
        return shareCode;
    }

    private boolean isVipNotExpired(Date dueTime) {
        return dueTime != null && (dueTime.getTime() >= new Date().getTime());
    }

}
