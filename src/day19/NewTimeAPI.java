package day19;

import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.TemporalAccessor;

public class NewTimeAPI {

    /*
    Date是jdk1.0中的，1.1用Calendar替换
    但是两者都有缺点：
        可变性：日期应该是不可变的
        偏移性：Date是从1900年开始的，构造中的year会加上1900
        格式化：只对Date有用，Calendar没用
        此外，不是线程安全的，不能处理闰秒
    jdk8之后添加了Joda-Time包
        8之前的需要配置maven
        java.time中的API
    Instant 一个瞬时的点，从1970年开始
    DateTimeFormatter：格式化
     */

    @Test
    public void testTime(){
        LocalDate now = LocalDate.now();
        LocalTime localTime = LocalTime.now();
        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDateTime.of(2020,10,3,23,56);
        //设定指定的时间没有偏移量
        localDateTime.getDayOfMonth();
        localDateTime.getMonthValue();
        LocalDateTime ldt1 = localDateTime.withHour(22);
        //和String一样，不可不变性
        LocalDateTime ldt2 = localDateTime.plusDays(2);
        LocalDateTime ldt3 = localDateTime.minusDays(3);
    }

    @Test
    public void testInstant(){
        Instant instant = Instant.now();
        System.out.println(instant);//差了八个小时，本初子午线的时间
        OffsetDateTime ins1 = instant.atOffset(ZoneOffset.ofHours(8));
        System.out.println(ins1);
        //获取1970-1-1开始的毫秒数
        instant.toEpochMilli();

        //通过给定的毫秒数获取实例
        Instant in2 = Instant.ofEpochMilli(instant.toEpochMilli());

    }

    @Test
    public void testFormatter(){
        DateTimeFormatter dtf1 = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        LocalDateTime ldt1 = LocalDateTime.now();
        dtf1.format(ldt1);
        dtf1.parse("2010-02-18T14:22:43.797");

        DateTimeFormatter dtf2 = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT);

        String s2 = dtf2.format(LocalDateTime.now());
        System.out.println(s2);

        DateTimeFormatter dtf3 = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
        String s3 = dtf3.format(LocalDateTime.now());
        TemporalAccessor ta = dtf3.parse("yyyy-MM-dd hh:mm:ss");

    }


}
