package com.sf.jfinal.qs.core.tools.weixin;

import com.sf.jfinal.qs.core.tools.PathKit;
import com.jfinal.weixin.sdk.api.AccessTokenApi;
import com.jfinal.weixin.sdk.api.ApiResult;
import com.jfinal.weixin.sdk.api.CustomServiceApi;
import com.jfinal.weixin.sdk.utils.HttpUtils;
import com.jfinal.weixin.sdk.utils.JsonUtils;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyCustomServiceApi extends CustomServiceApi {
    private static String customMessageUrl = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=";

    public static Map<String, Object> addKfAccount() {
        ApiResult result1 = addKfAccount("WxCardMM", "cc", "cc123");
        ApiResult result2 = uploadKfAccountHeadImg("WxCardMM", new File(PathKit.getPublicDir("public/images/wclogo_bg.png")));
        Map<String, Object> ret = new HashMap<>();
        ret.put("ADD", result1);
        ret.put("UD", result2);
        return ret;
    }

    /**
     * Send news message to customer specified by openid by.
     * @param kfAccount kefu account
     */
    public static ApiResult sendNews(String kfAccount, String openId, List<Articles> articles) {
        HashMap<String, Object> json = new HashMap<>();
        json.put("touser", openId);
        json.put("msgtype", "news");
        HashMap<String, Object> news = new HashMap<>();
        news.put("articles", articles);
        json.put("news", news);
        String accessToken = AccessTokenApi.getAccessTokenStr();
        String jsonResult = HttpUtils.post(customMessageUrl + accessToken, JsonUtils.toJson(json));
        return new ApiResult(jsonResult);
    }
}
