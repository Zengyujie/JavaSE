package day20;

import org.junit.Test;

import java.lang.annotation.Annotation;
import java.util.ArrayList;

public class AnnotationTest {

    /*
    1，Annotation是代码里的特殊标记，可以在编译，类加载，运行时被读取，并执行相应的处理
    可以像修饰符一样被使用，可用于修饰包，类，构造器，方法，成员变量，参数，局部变量的声明
    2，JPA(java持久化api)，spring2.5以上等都是基于注解
    框架=注解+反射+设计模式
    3，Annotation是jkd5.0新增的
    4，注解实例：
        1，文档，
        2，jdk内置三个基本注解
            @Override:编译时前置校验，用在方法上,
            @Deprecated:表示方法过时了或结构危险，但是没有删掉
            @SuppressWarnings("parameter"):抑制编译器警告，可以加在变量和方法上,可加参数，
    5，自定义注解
    6，jdk定义的四个元注解meta-annotation，元注解是对现有注解解释说明的注解，分别是：
        1，Retention:
            只能用于修饰一个Annotation定义，用于指定该Annotation的声明周期，包含一个
            RetentionPolicy类型的成员变量，使用时必须为其赋值：
                1，SOURCE：在源文件中有效，编译时丢弃
                2，CLASS(默认值)：在class文件中有效，运行java程序时，JVM不会保留注释
                3，RUNTIME：运行时有效，运行java程序时，jvm会保留注释，程序可以通过反射获取该注释
                关系：source->javac编译->CLASS->java类加载->RUNTIME->反射
        2，Target:
            表明注解可以修饰那些结构，
        3，Documented:
            用于指定Annotation修饰的类会被javadoc工具提取成文档，默认情况下javadoc不保留注解
        4，Inherited:
            被它修饰的Annotation将具有继承性，其子类也具有继承性不用显示再加
     7，一般自定义注解都会有1，2
     8，jkd1.8中的新注解：
        1，可重复注解
            再MyAnnotation上声明@Repeatable，成员值为MyAnnocations.class
            MyAnnotation和MyAnnotations的@Target和@Retention相同
        2，类型注解@Target中的新特性
            ElementType.TYPE_PARAMETER：表示该注解能写在类型变量的声明语句中
            ElementType.TYPE_USE：表示该注解能写在使用类型的任何语句中
     */
    @SuppressWarnings("unused")
    public void test(){

    }

    @Test
    public void test1(){
        Class<Student> pclass = Student.class;
        Annotation[] arr = pclass.getAnnotations();
        for(Annotation a : arr){
            System.out.println(a);
        }
    }

}

//@MyAnnotation(value="person1")
@MyAnnotation(value="person1")
@MyAnnotation(value="person2")
class Person{

}


class Student extends Person{

}

//@MyAnnotations(values = {@MyAnnotation(value="test"), @MyAnnotation()})
class Teacher{

}

//TYPE_PARAMETER可修饰泛型中的部分
class Generic<@MyAnnotation T>{

    //TYPE_UES可修饰抛出异常，变量，说明它们的信息
    public void show() throws @MyAnnotation RuntimeException{
        ArrayList<@MyAnnotation String> list = new ArrayList<@MyAnnotation String>();
        int num = (@MyAnnotation int)10;
    }
}