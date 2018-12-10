package com.sf.jfinal.qs.controller.weixin;

import com.jfinal.log.Log;
import com.jfinal.weixin.sdk.api.ApiConfig;
import com.sf.jfinal.qs.core.tools.weixin.ApiConfigFactory;
import com.sf.jfinal.qs.core.tools.weixin.SFWxKit;

public class WeixinApiController extends WxJSSDKSupportController {
    private static Log log = Log.getLog(WeixinApiController.class);

    public ApiConfig getApiConfig() {
        return ApiConfigFactory.create(SFWxKit.CONFIG_SFWx);
    }
}
