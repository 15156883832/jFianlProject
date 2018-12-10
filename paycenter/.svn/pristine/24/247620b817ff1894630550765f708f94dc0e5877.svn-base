package com.sf.jfinal.qs.core.tools.weixin;

import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;

public class ApiConfigFactory {

    public static com.jfinal.weixin.sdk.api.ApiConfig create(String configFile) {
        com.jfinal.weixin.sdk.api.ApiConfig ac = new com.jfinal.weixin.sdk.api.ApiConfig();

        // 配置微信 API 相关常量
        Prop config = PropKit.use(configFile);
        ac.setToken(config.get("token"));
        ac.setAppId(config.get("appId"));
        ac.setAppSecret(config.get("appSecret"));

        //  是否对消息进行加密，对应于微信平台的消息加解密方式：
        //  1：true进行加密且必须配置 encodingAesKey
        //  2：false采用明文模式，同时也支持混合模式
        ac.setEncryptMessage(config.getBoolean("encryptMessage", false));
        ac.setEncodingAesKey(config.get("encodingAesKey", "setting it in config file"));
        return ac;
    }
}
