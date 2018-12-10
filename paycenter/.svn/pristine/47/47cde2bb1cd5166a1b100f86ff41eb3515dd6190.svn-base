package com.sf.jfinal.qs.core.tools;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.Random;
import java.util.UUID;

/**
 * @author gaols
 */
public class RandomKit extends RandomStringUtils {
    public static String UUID() {
        String jdkUuid = UUID.randomUUID().toString();
        StringBuilder sb = new StringBuilder("");
        sb.append("#").append(System.nanoTime()).append("#").append(jdkUuid);
        for (int i = 0; i < 20; i++) {
            int tmp = new Random().nextInt(10000);
            sb.append("#").append(tmp);
        }
        return SecurityKit.MD5(sb.toString() + System.currentTimeMillis());
    }
}
