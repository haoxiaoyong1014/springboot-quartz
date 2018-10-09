package com.example.quartz.tool;

import org.quartz.DateBuilder;
import org.springframework.stereotype.Component;

/**
 * Created by haoxy on 2018/10/8.
 * E-mail:hxyHelloWorld@163.com
 * github:https://github.com/haoxiaoyong1014
 */
@Component
public class DateUnit {

    public DateBuilder.IntervalUnit verification(Integer timeType) {
        switch (timeType) {
            case 1:
                DateBuilder.IntervalUnit year = DateBuilder.IntervalUnit.YEAR;
                return year;
            case 2:
                DateBuilder.IntervalUnit month = DateBuilder.IntervalUnit.MONTH;
                return month;
            case 3:
                DateBuilder.IntervalUnit day = DateBuilder.IntervalUnit.DAY;
                return day;
            case 4:
                DateBuilder.IntervalUnit hour = DateBuilder.IntervalUnit.HOUR;
                return hour;
            case 5:
                DateBuilder.IntervalUnit second = DateBuilder.IntervalUnit.MINUTE;
                return second;
            case 6:
                DateBuilder.IntervalUnit week = DateBuilder.IntervalUnit.WEEK;
                return week;
            default:
                return DateBuilder.IntervalUnit.SECOND;
        }
    }

}
