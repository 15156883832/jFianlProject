package com.sf.jfinal.qs.core.tools;

import org.apache.commons.lang3.time.DateUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DateKit extends DateUtils {
    public static final String DATETIME_24H = "yyyy-MM-dd HH:mm:ss";
    public static final String DATETIME_24H_zh = "yyyy年MM月dd日 HH:mm";
    public static final String DATETIME_12H = "yyyy-MM-dd hh:mm:ss";
    public static final String DATE = "yyyy-MM-dd";
    public static final String TIME_24H = "HH:mm:ss";
    public static final String TIME_12H = "hh:mm:ss";
    private static final Map<String, String> DFM = new HashMap<String, String>();
    private static String[] parsePatterns = new String[]{"yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm"};

    private static void initDFM() {
        DFM.put("/datetime", "yyyy/MM/dd HH:mm:ss");
        DFM.put("-datetime", "yyyy-MM-dd HH:mm:ss");
        DFM.put("-datetime-", "yyyy-MM-dd HH:mm");
        DFM.put("-date", "yyyy-MM-dd");
        DFM.put("/date", "yyyy/MM/dd");
        DFM.put("date", "yyyyMMdd");
        DFM.put("-date-", "yyyy-MM");
        DFM.put("/date-", "yyyy/MM");
        DFM.put("time", "HH:mm:ss");
        DFM.put("time-", "HH:mm");
        DFM.put("time--", "HH");
    }

    static {
        initDFM();
    }

    public static Date parseDateL(String date, boolean soft) {
        try {
            return parseDate(date, parsePatterns);
        } catch (ParseException e) {
            if (soft) {
                return null;
            }

            throw new RuntimeException(e);
        }
    }

    public static boolean isToday(Date date) {
        return isSameDay(date, Calendar.getInstance().getTime());
    }

    public static Date silentParse(DateFormat df, String date) {
        if (StrKit.isBlank(date)) {
            return null;
        }
        try {
            return df.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException("Invalid date found:" + date);
        }
    }

    public static Calendar tomorrow() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, 1);
        c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DATE), 0, 0, 0);
        return c;
    }

    public static Calendar today() {
        Calendar c = Calendar.getInstance();
        c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DATE), 0, 0, 0);
        return c;
    }

    public static Date endDayOfMonth() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, 1);
        c.set(Calendar.DAY_OF_MONTH, 1);
        c.add(Calendar.DAY_OF_MONTH, -1);
        return c.getTime();
    }

    public static DateFormat getDateFormat(String xpattern) {
        initDFM();
        String pattern = DFM.get(xpattern);
        if (pattern == null) {
            pattern = xpattern;
        }
        return new SimpleDateFormat(pattern);
    }

    public static Date beginningDayOfMonth() {
        return beginningDayOfMonth(null);
    }

    public static String dateConv(String src, DateFormat fsrc, DateFormat fdest) {
        if (StrKit.isBlank(src)) {
            return src;
        }

        try {
            Date date = fsrc.parse(src);
            return fdest.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static String dateConv(String src, String fsrc, String fdest) {
        return dateConv(src, getDateFormat(fsrc), getDateFormat(fdest));
    }

    public static Date xparse(String dateStr, String pattern) {
        DateFormat df = getDateFormat(pattern);
        return parse(dateStr, df);
    }

    public static Date parse(String dateStr, DateFormat df) {
        Date date;
        try {
            date = df.parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return date;
    }

    public static String xformat(Date date, String xpattern) {
        return format(date, getDateFormat(xpattern));
    }

    public static String format(Date date, String pattern) {
        DateFormat df = new SimpleDateFormat(pattern);
        return df.format(date);
    }

    public static String format(Date date, DateFormat df) {
        return df.format(date);
    }

    public static Date beginningDayOfMonth(Date d) {
        Calendar c = Calendar.getInstance();
        if (d != null) {
            c.setTime(d);
        }
        c.set(Calendar.DAY_OF_MONTH, 1);
        return c.getTime();
    }
}
