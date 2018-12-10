package com.sf.jfinal.qs.controller.weixin;

import com.jfinal.kit.HashKit;
import com.jfinal.log.Log;
import com.jfinal.weixin.sdk.api.*;
import com.jfinal.weixin.sdk.jfinal.ApiController;
import com.sf.jfinal.qs.core.tools.weixin.WxKit;

import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

public abstract class WxJSSDKSupportController extends ApiController {
    private static Log log = Log.getLog(WxJSSDKSupportController.class);

    /**
     * 微信JS-SDK配置的时候需要的一些参数。
     */
    protected void wxConfig() {
        String _wxShareUrl = WxKit.getFullURL(getRequest()).split("#")[0];
        JsTicket jsTicket = JsTicketApi.getTicket(JsTicketApi.JsApiType.jsapi);
        String _wxJsApiTicket = jsTicket.getTicket();

        Map<String, String> _wxMap = new TreeMap<>();
        String _wxNoncestr = UUID.randomUUID().toString().replace("-", ""); // noncestr
        String _wxTimestamp = String.valueOf(System.currentTimeMillis() / 1000); // timestamp
        _wxMap.put("noncestr", _wxNoncestr);
        _wxMap.put("timestamp", _wxTimestamp);
        _wxMap.put("jsapi_ticket", _wxJsApiTicket);
        _wxMap.put("url", _wxShareUrl);

        // 加密获取signature
        StringBuilder _wxBaseString = new StringBuilder();
        for (Map.Entry<String, String> param : _wxMap.entrySet()) {
            _wxBaseString.append(param.getKey()).append("=").append(param.getValue()).append("&");
        }
        String _wxSignString = _wxBaseString.substring(0, _wxBaseString.length() - 1);
        String _wxSignature = HashKit.sha1(_wxSignString); // signature

        ApiConfig apiConfig = getApiConfig();
        setAttr("appId", apiConfig.getAppId());
        setAttr("timestamp", _wxTimestamp);
        setAttr("nonceStr", _wxNoncestr);
        setAttr("signature", _wxSignature);
        setAttr("url", _wxShareUrl);
        log.warn("signature=" + _wxSignature + ";_wxShareUrl=" + _wxShareUrl);
    }

    protected SnsAccessToken getSnsToken() {
        ApiConfig config = getApiConfig();
        String code = getPara("code");
        return SnsAccessTokenApi.getSnsAccessToken(config.getAppId(), config.getAppSecret(), code);
    }
}
