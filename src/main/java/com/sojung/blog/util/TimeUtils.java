package com.sojung.blog.util;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneOffset;
import java.util.Date;

public class TimeUtils {

    public static String getCurrentISO8601MilliPlusTime(){
        return Instant.now().atOffset(ZoneOffset.ofHours(9)).toString();
    }

    public static String convert_yyyyMMddToISO8601MilliPlusTime(String yyyyMMdd){
        SimpleDateFormat sourceDateFormat = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat resultDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        Date date;
        try {
            date = sourceDateFormat.parse(yyyyMMdd);
            return resultDateFormat.format(date);
        }catch(Exception e){
            e.printStackTrace();
            return yyyyMMdd;
        }
    }

    public static String getCurrentISO8601MilliPlusTimeMinusMilli(Long milliSecond){
        return Instant.now().minusMillis(milliSecond).atOffset(ZoneOffset.ofHours(9)).toString();
    }
}
