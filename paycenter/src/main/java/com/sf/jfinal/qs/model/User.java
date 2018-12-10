package com.sf.jfinal.qs.model;

import com.sf.jfinal.qs.model.base.BaseUser;

/**
 * 用户类型(0.超级管理员1.运营人员2.网点3.PC使用人员(旧的为信息专员)4.服务工程师 !(去掉5.6.7)5.区域管理员 6.配件管理员 7.商品供应商)
 */
@SuppressWarnings("serial")
public class User extends BaseUser<User> {
    public static final User dao = new User();

    public User findById(String uid) {
        return dao.findFirst("select * from sys_user where id=? and `status`='0'", uid);
    }

    enum UserType {
        su, // 超级管理员
        system, // 运营人员
        site, // 网点
        pc, // PC使用人员(旧的为信息专员)
        employee, // 服务工程师
        areaManager, // 区域管理员
        fittingManager, // 配件管理员
        vendor // 商品供应商
    }
}
