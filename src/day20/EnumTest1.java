package day20;

import java.util.Arrays;

public class EnumTest1 {
    /*
    枚举与注解都是jdk5.0的新特性
    1，枚举enum
        当需要定义一组常量时，强烈建议使用枚举类
        如果枚举类中只有一个对象，则可以作为单例模式
        定义的枚举类继承于java.lang.Enum
        enum中的常用方法,values, valueOf
        enum中实现接口
            情况一：让定义枚举实现抽象方法
            情况二：让每个枚举对象重写抽象方法
     */
    public static void main(String[] args) {
        Season autumn = Season.AUTUMN;
        MySeason my = MySeason.AUTUMN;
        System.out.println(my);
        System.out.println(Arrays.toString(MySeason.values()));
        //如果没有枚举类对象就抛出异常
        System.out.println(MySeason.valueOf("WINTER"));
    }



}

class Season{

    //1，生命对象的属性，用private final修饰
    private final String seasonName;
    private final String seasonDesc;


    //2，私有化构造器并赋值
    private Season(String seasonName, String seasonDesc){
        this.seasonDesc = seasonDesc;
        this.seasonName = seasonName;
    }

    //3，提供当前枚举的多个对象
    public static final Season SPRING = new Season("spring","spring1");
    public static final Season SUMMER = new Season("summer","summer1");
    public static final Season AUTUMN = new Season("autumn","autumn1");
    public static final Season WINTER = new Season("winter","winter1");

    //4，提供方法


    public String getSeasonName() {
        return seasonName;
    }

    public String getSeasonDesc() {
        return seasonDesc;
    }

    @Override
    public String toString() {
        return "Season{" +
                "seasonName='" + seasonName + '\'' +
                ", seasonDesc='" + seasonDesc + '\'' +
                '}';
    }
}


enum MySeason implements Info{
    SPRING("spring","spring1"),SUMMER("summer","summer1"),
    AUTUMN("autumn","autumn1"),WINTER("winter","winter1");

    private final String seasonName;
    private final String seasonDesc;


    //2，私有化构造器并赋值
    private MySeason(String seasonName, String seasonDesc){
        this.seasonDesc = seasonDesc;
        this.seasonName = seasonName;
    }

    public String getSeasonName() {
        return seasonName;
    }

    public String getSeasonDesc() {
        return seasonDesc;
    }

    @Override
    public void show() {

    }

//    @Override
//    public String toString() {
//        return "Season{" +
//                "seasonName='" + seasonName + '\'' +
//                ", seasonDesc='" + seasonDesc + '\'' +
//                '}';
//    }


}

interface Info{
    void show();
}


enum MySeason2 implements Info{
    SPRING("spring","spring1"){
        @Override
        public void show() {
            System.out.println("my show1");
        }
    },SUMMER("summer","summer1"){
        @Override
        public void show() {
            System.out.println("myshow2");
        }
    },
    AUTUMN("autumn","autumn1"),WINTER("winter","winter1");

    private final String seasonName;
    private final String seasonDesc;


    //2，私有化构造器并赋值
    private MySeason2(String seasonName, String seasonDesc){
        this.seasonDesc = seasonDesc;
        this.seasonName = seasonName;
    }

    public String getSeasonName() {
        return seasonName;
    }

    public String getSeasonDesc() {
        return seasonDesc;
    }

    @Override
    public void show() {

    }

//    @Override
//    public String toString() {
//        return "Season{" +
//                "seasonName='" + seasonName + '\'' +
//                ", seasonDesc='" + seasonDesc + '\'' +
//                '}';
//    }


}