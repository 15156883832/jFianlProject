package com.sf.jfinal.qs.config;

import com.jfinal.config.Constants;
import com.jfinal.config.Routes;
import com.jfinal.weixin.sdk.api.ApiConfigKit;

public class WeixinConfig extends PluggableConfigAdapter {
    public boolean pluggable() {
        return ConfigKit.isWeixinDevEnabled();
    }

    @Override
    public void doConfigConstant(Constants me) {
        ApiConfigKit.setDevMode(me.getDevMode());
    }

    @Override
    public void doConfigRoute(Routes me) {
    }
}
