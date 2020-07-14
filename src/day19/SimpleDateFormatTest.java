package day19;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SimpleDateFormatTest {

    /*
    Date类的API不易于国际化，大部分被废弃了，SimpleDateFormate是一个不与语言环境有关的类
    1，两个操作：
        1.1格式化：日期-》字符串
        1.2解析：字符串-》日期
     */




    @Test
    public void test1() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat();
        Date date = new Date();
        String format = sdf.format(date);
        System.out.println(format);
        String str = "19-5-25 上午11:43";
        Date date1 = sdf.parse(str);
        System.out.println(date1);
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String format1 = sdf1.format(date);
        System.out.println(format1);
        //解析和格式化都必须和SimpleDateFormat创建的参数形式匹配
        //否则会抛异常
    }

    @Test
    public void test2() throws ParseException {
        String birth = "2020-09-08";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse(birth);
        java.sql.Date birthDate = new java.sql.Date(date.getTime());

    }

    @Test
    public void test3() throws ParseException{
        
    }

    @Test
    public void testCalender(){
        //Calender是一个abstract class
        //方式一：创建子类

        //方式二：
        Calendar calendar = Calendar.getInstance();
        System.out.println(calendar.getClass());
        //常用方法：
        //get() set() add()...
    }



}
