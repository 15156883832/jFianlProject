package com.sf.jfinal.qs.core.tools;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class SmsKit {
    public static final String NAME = "22154848";// 用户名
    public static final String PASSOWRD = "9FA6EB4D075A98F84F896CF0D451";// 密码

    private static boolean sendCode(String code, String mobile) {
        // 电话号码字符串，中间用英文逗号间隔
        StringBuffer mobileString = new StringBuffer(mobile);
        // 内容字符串
        StringBuffer msg = new StringBuffer("【思方科技】您正在进行手机验证，验证码为：");
        msg.append(code);
        msg.append("，5分钟内有效。");
        // 追加发送时间，可为空，为空为及时发送
        String stime = "";
        // 扩展码，必须为数字 可为空
        StringBuffer extno = new StringBuffer();
        try {
            String result = doPost(mobileString, msg, stime, extno);
            String[] spr = result.split(",");
            return "0".equals(spr[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public static String sendCode(String mobile) {
        String code = RandomKit.randomNumeric(4);
        return sendCode(code, mobile) ? code : null;
    }

    public static void sendSms(String mobile, StringBuffer msg) throws Exception {
        doPost(new StringBuffer(mobile), msg, "", new StringBuffer());
    }

    private static String doPost(StringBuffer mobileString, StringBuffer msg, String stime, StringBuffer extno) throws Exception {
        StringBuilder param = new StringBuilder();
        param.append("name=" + NAME);
        param.append("&pwd=" + PASSOWRD);
        param.append("&mobile=").append(mobileString);
        param.append("&content=").append(URLEncoder.encode(msg.toString(), "UTF-8"));
        param.append("&stime=").append(stime);
        param.append("&sign=").append(URLEncoder.encode("", "UTF-8"));
        param.append("&type=pt");
        param.append("&extno=").append(extno);

        URL localURL = new URL("http://web.wasun.cn/asmx/smsservice.aspx?");
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
                throw new Exception("HTTP Request error " + httpURLConnection.getResponseCode());
            }
            inputStream = httpURLConnection.getInputStream();
            resultBuffer = convertStreamToString(inputStream);
        } finally {
            IOUtils.closeQuietly(outputStreamWriter);
            IOUtils.closeQuietly(outputStream);
            IOUtils.closeQuietly(inputStream);
        }

        return resultBuffer;
    }

    /**
     * 转换返回值类型为UTF-8格式.
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
            IOUtils.closeQuietly(is);
        }
        return sb1.toString();
    }
}
