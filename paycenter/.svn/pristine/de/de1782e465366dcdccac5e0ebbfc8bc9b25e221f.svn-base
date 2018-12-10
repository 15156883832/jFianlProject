package com.sf.jfinal.qs.core.sms;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * sms.username=13955174824
 * sms.password=46961CD60F4FC09EE8ADA00BB722
 * sms.user_reply_extno=29371
 */
public class SMSUtils {

//    private static Logger logger = Logger.getLogger(SMSUtils.class);
//    public static final String splitor = "#@@#";
//    public static final String itemSplitor = "#@#";
//    public static final String sfSmsNo = "29371";

    private static String name = "13955174824";// 用户名
    private static String password_md5 = "46961CD60F4FC09EE8ADA00BB722";// 密码


    public static String sendMsg(String mobile, String content, String sign, String extnoStr) {
        // 电话号码字符串，中间用英文逗号间隔
        StringBuffer mobileString = new StringBuffer(mobile);
        // 内容字符串
        StringBuffer contextString = new StringBuffer(content);//"【思方科技】您正在进行手机验证，验证码为："+ code + "，5分钟内有效。"
        // 追加发送时间，可为空，为空为及时发送
        String stime = "";
        // 扩展码，必须为数字 可为空
        StringBuffer extno = new StringBuffer(extnoStr);
        try {
            return doPost(mobileString, contextString, "pt", stime, null, extno, sign);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 发送短信
     *
     * @param mobileString  电话号码字符串，中间用英文逗号间隔
     * @param contextString 内容字符串
     * @param sign          签名
     * @param stime         追加发送时间，可为空，为空为及时发送
     * @param extno         扩展码，必须为数字 可为空
     */
    private static String doPost(StringBuffer mobileString,
                                 StringBuffer contextString, String type, String stime, String tid,
                                 StringBuffer extno, String sign) throws Exception {// 2016/12/15 14:18:53
        StringBuilder param = new StringBuilder();
        param.append("name=").append(name);
        param.append("&pwd=").append(password_md5);
        param.append("&mobile=").append(mobileString);
        param.append("&content=").append(URLEncoder.encode(contextString.toString(), "UTF-8"));
        param.append("&stime=").append(stime);
        param.append("&sign=").append(URLEncoder.encode(sign, "UTF-8"));
        param.append("&type=").append(type);
        param.append("&tid=").append(tid);
        param.append("&extno=").append(extno);

        URL localURL = new URL("http://210.5.152.195:1861/asmx/smsservice.aspx?");//http://web.wasun.cn/asmx/smsservice.aspx?
        URLConnection connection = localURL.openConnection();
        HttpURLConnection httpURLConnection = (HttpURLConnection) connection;

        httpURLConnection.setDoOutput(true);
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setRequestProperty("Accept-Charset", "utf-8");
        httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        httpURLConnection.setRequestProperty("Content-Length", String.valueOf(param.length()));

        OutputStream outputStream = null;
        OutputStreamWriter outputStreamWriter = null;
        InputStream inputStream = null;
        String resultBuffer = "";

        try {
            outputStream = httpURLConnection.getOutputStream();
            outputStreamWriter = new OutputStreamWriter(outputStream);

            outputStreamWriter.write(param.toString());
            outputStreamWriter.flush();

            if (httpURLConnection.getResponseCode() >= 300) {
                throw new Exception("HTTP Request is not success, Response code is:" + httpURLConnection.getResponseCode());
            }
            inputStream = httpURLConnection.getInputStream();
            resultBuffer = convertStreamToString(inputStream);
        } finally {
            if (outputStreamWriter != null) {
                outputStreamWriter.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return resultBuffer;
    }

    /**
     * 转换返回值类型为UTF-8格式.
     *
     * @param is input stream
     */
    private static String convertStreamToString(InputStream is) {
        StringBuilder sb1 = new StringBuilder();
        byte[] bytes = new byte[4096];
        int size;

        try {
            while ((size = is.read(bytes)) > 0) {
                String str = new String(bytes, 0, size, "UTF-8");
                sb1.append(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb1.toString();
    }

}
