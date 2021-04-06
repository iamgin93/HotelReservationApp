package com.ginsoon.learnspringboot.web;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    public static Date createDateFromString(String dateString){
        Date date = null;

        if (dateString != null){
            try {
                date = DATE_FORMAT.parse(dateString);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }else {
            date = new Date();
        }
        return date;
    }
}
