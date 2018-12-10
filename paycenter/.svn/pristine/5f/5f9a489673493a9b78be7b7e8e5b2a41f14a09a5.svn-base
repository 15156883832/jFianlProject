package com.sf.jfinal.qs.service;

import com.sf.jfinal.qs.config.ConfigKit;
import com.sf.jfinal.qs.config.EmailConfig;
import com.sf.jfinal.qs.core.tools.SecurityKit;
import com.sf.jfinal.qs.core.tools.StrKit;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.shiro.crypto.hash.SimpleHash;

import javax.servlet.http.HttpServletRequest;

public class MailService {
    /**
     * 通过邮箱找回密码
     */
    public void sendResetPasswordEmail(HttpServletRequest req, String token, String to) throws EmailException {
        EmailConfig config = ConfigKit.getEmailConfig();
        HtmlEmail email = new HtmlEmail();
        // this is really important
        email.setCharset("UTF-8");
        email.setHostName(config.getHost());
        email.setAuthenticator(new DefaultAuthenticator(config.getUsername(), config.getPassword()));
        email.setFrom(config.getUsername(), "QSConfig");
        email.setSubject("QSConfig 密码重置");

        email.setHtmlMsg(buildContent(buildHref(req, token, to, config)));
        email.addTo(to);
        email.send();
    }

    private String buildHref(HttpServletRequest req, String token, String to, EmailConfig config) {
        String ctxPath = req.getContextPath();
        String basePath = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + ctxPath;
        String emailHex = SecurityKit.hex(StrKit.getBytes(to, "UTF-8"));
        String href = basePath + "/user/resetPassword/" + token + "-" + emailHex;
        String signature = new SimpleHash("SHA-1", token + "-" + emailHex, config.getSalt()).toString();
        href += "-" + signature;
        return href;
    }

    private String buildContent(String href) {
        String sb = "<!DOCTYPE html><html>" +
                "<head>" +
                "<meta charset='utf-8'>" +
                "</head>" +
                "<body>" +
                "请勿回复该邮件，<a target='_BLANK' href=\"" +
                href +
                "\">点击这里</a>重置密码！" +
                "</body>" +
                "</html>";
        return sb;
    }
}
