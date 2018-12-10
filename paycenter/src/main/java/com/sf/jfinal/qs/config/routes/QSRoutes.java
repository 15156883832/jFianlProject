package com.sf.jfinal.qs.config.routes;

import com.jfinal.config.Routes;
import com.sf.jfinal.qs.controller.*;

/**
 * 本项目系统路由
 */
public class QSRoutes extends Routes {
    @Override
    public void config() {
        add("/notify/tp", TPNotifyController.class);
        add("/notify/sms", SmsNotifyController.class);
        add("/notify/code", CodeNotifyController.class);
        add("/notify/cop", COPNotifyController.class);
        add("/notify/sg", SFSignUpController.class);//服务商激活码注册开通VIP
        add("/", IndexController.class); 
        add("/notify/pt", PlatformOrderController.class);
        add("/notify/sf", SFNotifyController.class);//开通/续费思方VIP
        add("/notify/nandao", NanDaoNotifyController.class);//南岛漏电保护插头
        add("/notify/detergent", DetergentNotifyController.class);//洁力士清洁剂
        add("/notify/faucet", FaucetNotifyController.class);//水龙头
        add("/notify/flavorbox", FlavorBoxController.class);//除味盒
        add("/notify/refrigerator", RefrigeratorController.class);//美的冰箱
    }
}
