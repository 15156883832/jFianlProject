package com.sf.jfinal.qs.core.tools.weixin;

import com.sf.jfinal.qs.core.tools.PathKit;
import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;
import com.jfinal.weixin.sdk.api.SnsAccessTokenApi;
import com.jfinal.weixin.sdk.utils.Charsets;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class SFWxKit {
    public static final String CONFIG_SFWx = "SFWx.txt";

    /**
     * @param redirectUri the relative url without domain
     */
    public static String getAuthorizeURL(String redirectUri, boolean snsapiBase) {
        try {
            Prop prop = PropKit.use(CONFIG_SFWx);
            String appid = prop.get("appId");
            String domain = prop.get("domain");
            String finalUrl = urlEncode(PathKit.join(domain, redirectUri));
            return SnsAccessTokenApi.getAuthorizeURL(appid, finalUrl, snsapiBase);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public static String urlEncode(String src) throws UnsupportedEncodingException {
        return URLEncoder.encode(src, Charsets.UTF_8.name()).replace("+", "%20");
    }

    public static String getDomain() {
        return PropKit.use(CONFIG_SFWx).get("domain");
    }
}
