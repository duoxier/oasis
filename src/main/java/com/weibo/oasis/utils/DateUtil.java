package com.weibo.oasis.utils;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class DateUtil {
    public static Date getTokenExpireTime(){
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(new Date());
        rightNow.add(Calendar.DAY_OF_YEAR,7);
        return rightNow.getTime();
    }

    public static Date formatYYYYMMDD(String date){
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            return sdf.parse(date);
        }catch (ParseException e){
            return null;
        }
    }

    public static String formatYYYYMMDD(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);

    }

    public static String formatYYYYMMDDHHMMSS(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);

    }

    public static Date formatYYYYMMDDhhmmss(String date){
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return sdf.parse(date);
        }catch (ParseException e){
            return null;
        }
    }

    public static  String todayOfEnd(Date date){
        if (date != null){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
            return sdf.format(date);
        }
        return null;
    }

}
