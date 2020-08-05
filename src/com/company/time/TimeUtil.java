package com.company.time;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

public class TimeUtil {
    public static void main(String[] args) {
        //计算时间差
        long timestamp = 1530771236000L;
        LocalDate localDate = Instant.ofEpochMilli(timestamp).atZone(ZoneId.systemDefault()).toLocalDate();

        LocalDate startDate = localDate;
        System.out.println("开始时间  : " + startDate);

        LocalDate endDate = LocalDate.now();
        System.out.println("结束时间 : " + endDate);

        long daysDiff = ChronoUnit.DAYS.between(startDate, endDate);
        System.out.println("两天之间的差在天数   : " + daysDiff);
    }
}
