package com.sf.jfinal.qs.core.tools;

import java.util.ArrayList;
import java.util.List;

public class ArraysKit {
    public static <T> List<T> trimNull(T[] array) {
        if (array == null) {
            return new ArrayList<>();
        }

        List<T> ret = new ArrayList<>();
        for (T t : array) {
            if (t != null) {
                ret.add(t);
            }
        }

        return ret;
    }

    public static List<String> trimBlank(String[] array) {
        if (array == null) {
            return new ArrayList<>();
        }

        List<String> ret = new ArrayList<>();
        for (String e : array) {
            if (StrKit.isNotBlank(e)) {
                ret.add(e);
            }
        }

        return ret;
    }
}
