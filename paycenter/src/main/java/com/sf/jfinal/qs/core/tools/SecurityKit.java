package com.sf.jfinal.qs.core.tools;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.codec.Hex;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class SecurityKit {
    public static String randomHex(int len) {
        if (len < 2) {
            throw new RuntimeException("Minimum length is 2");
        }

        int _len = len;
        if (len % 2 != 0) {
            _len += 1;
        }
        byte[] buff = new byte[_len / 2];
        SecureRandom random = new SecureRandom();
        random.nextBytes(buff);
        return Hex.encodeToString(buff).substring(0, len);
    }

    public static byte[] randomBytes(int len) {
        byte[] buff = new byte[len];
        SecureRandom random = new SecureRandom();
        random.nextBytes(buff);
        return buff;
    }

    public static String genUniqueNo() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        return sdf.format(new Date()) + getPositiveRandomWithRang(4);
    }

    private static String getPositiveRandomWithRang(int count) {
        StringBuilder sb = new StringBuilder();
        String str = "0123456789";
        Random r = new Random();
        for (int i = 0; i < count; i++) {
            int num = r.nextInt(str.length());
            sb.append(str.charAt(num));
            str = str.replace((str.charAt(num) + ""), "");
        }
        return sb.toString();
    }

    public static String hex(byte[] bytes) {
        return Hex.encodeToString(bytes);
    }

    public static byte[] hexToBytes(String hex) {
        return Hex.decode(hex);
    }

    public static byte[] merge(byte[] a, byte[] b) {
        byte[] ret = new byte[a.length + b.length];
        System.arraycopy(a, 0, ret, 0, a.length);
        System.arraycopy(b, 0, ret, a.length, b.length);
        return ret;
    }

    public static String MD5(String pwd) {
        char[] md5String = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

        try {
            byte[] e = pwd.getBytes();
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            mdInst.update(e);
            byte[] md = mdInst.digest();
            int j = md.length;
            char[] str = new char[j * 2];
            int k = 0;

            for (byte byte0 : md) {
                str[k++] = md5String[byte0 >>> 4 & 15];
                str[k++] = md5String[byte0 & 15];
            }

            return new String(str);
        } catch (Exception var10) {
            return null;
        }
    }

    public static String AESEncrypt(String value, String key) {
        try {
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
            byte[] encrypted = cipher.doFinal(value.getBytes());
            return Base64.encodeToString(encrypted);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
