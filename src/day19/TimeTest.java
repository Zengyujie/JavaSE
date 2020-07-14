package day19;

import java.util.Date;

public class TimeTest {

    /*
    时间

    JDK8之前的版本
    1，System.currentTimeMillis()
    2，java.util.Date():


     */

    public static void main(String[] args) {

        long time = System.currentTimeMillis();//1970-1-1至今的毫秒
        System.out.println(time);

        Date date1 = new Date();//当前时间
        System.out.println(date1);
        System.out.println(date1.getTime());//currentTimeMillis()
        Date date2 = new Date(System.currentTimeMillis());
        //Date其他构造器是deprecated方法，用Calender来代替了


    }


}
