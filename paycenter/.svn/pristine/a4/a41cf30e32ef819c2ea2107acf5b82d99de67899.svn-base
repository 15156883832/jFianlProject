package com.sf.jfinal.qs.core.tools.weixin;

import com.jfinal.log.Log;

import javax.servlet.http.HttpServletRequest;

public class WxKit {
    private static Log log = Log.getLog(WxKit.class);

    public static boolean isWxUserAgent(HttpServletRequest request) {
        String userAgent = request.getHeader("User-Agent");
        return userAgent.contains("MicroMessenger");
    }

    public static String getFullURL(HttpServletRequest request) {
        StringBuffer requestURL = request.getRequestURL();
        String qs = request.getQueryString();
        log.warn("requestURL=" + requestURL + ";qs=" + qs);
        return qs == null ? requestURL.toString() : requestURL.append('?').append(qs).toString();
    }
  /*  public static String getFullURL(HttpServletRequest request) {
        StringBuffer requestURL = request.getRequestURL();
        String qs = request.getQueryString();
        log.warn("requestURL=" + requestURL + ";qs=" + qs);
        String ret = qs == null ? requestURL.toString() : requestURL.append('?').append(qs).toString();
        ret = ret.replace("www.sifangerp.com/", "www.sifangerp.com/sfwx/"); // hack
        return ret;
    }*/
}
