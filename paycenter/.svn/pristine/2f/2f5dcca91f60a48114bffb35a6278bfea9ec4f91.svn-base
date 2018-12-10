package com.sf.jfinal.qs.core.tools;

import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * More String utilities.
 *
 * @author gaols
 */
public class StrKit extends StringUtils {
    /**
     * I love overkill!
     */
    public static final String SLASH = "/";

    public static String strip(String str) {
        return str.trim();
    }

    public static boolean isNotBlank(String str) {
        return !com.jfinal.kit.StrKit.isBlank(str);
    }

    public static boolean isNull(String val) {
        return val == null;
    }

    public static String trimNull(String val) {
        return isNull(val) ? "" : val;
    }

    public static byte[] getBytes(String str, String charset) {
        try {
            return str.getBytes(charset);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 将令人厌恶的checked exception转换为RuntimeException。
     */
    public static String fromBytes(byte[] bytes, String charset) {
        try {
            return new String(bytes, charset);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 连接字符串，譬如：[1,2,3]以逗号连接的结果为1,2,3。没想到我的的语文表达能力如此之强，“譬如”用的多么恰如其分？
     *
     * @param array     待连接的数组
     * @param separator 连接分隔符
     */
    public static <T> String join(List<T> array, String separator) {
        if (array == null || array.size() == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0, n = array.size(); i < n; i++) {
            if (i > 0) {
                sb.append(separator);
            }
            sb.append(array.get(i));
        }
        return sb.toString();
    }

    public static String joinNoneBlank(List<String> array, String separator) {
        if (array == null) {
            return "";
        }

        List<String> tmp = new ArrayList<>();
        for (String value : array) {
            if (StrKit.isNotBlank(value)) {
                tmp.add(value);
            }
        }
        return join(tmp, separator);
    }

    public static String joinNoneBlank(String separator, String... array) {
        if (array == null) {
            return "";
        }

        return joinNoneBlank(Arrays.asList(array), separator);
    }

    /**
     * @param base  base path
     * @param paths paths to be joined to base path
     * @return the joined path
     */
    public static String pathJoin(String base, String... paths) {
        StringBuilder sb = new StringBuilder();
        sb.append(base.endsWith(SLASH) ? StrKit.stripEnd(base, SLASH) : base);
        for (String path : paths) {
            String _path = path;
            if (!_path.startsWith(SLASH)) {
                _path = SLASH + _path;
            }
            if (_path.endsWith(SLASH)) {
                _path = StrKit.stripEnd(_path, SLASH);
            }
            sb.append(_path);
        }

        if (paths.length > 0 && paths[paths.length - 1].endsWith(SLASH)) {
            sb.append(SLASH);
        }
        return paths.length == 0 ? base : sb.toString();
    }
}
