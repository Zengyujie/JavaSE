package day24;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.*;
import java.util.Properties;
import java.util.Random;

import static java.lang.annotation.ElementType.*;

public class ReflectTest1 {
    /*
    
     */

    @Test
    public void test1() throws Exception{
        Properties pros =  new Properties();
        //此时文件默认在当前module下，src的同级目录下
        FileInputStream fis = new FileInputStream(new File("test.properties"));
        pros.load(fis);
        String user = pros.getProperty("user");
        String passwd = pros.getProperty("password");

        ClassLoader loader = ReflectTest1.class.getClassLoader();
        InputStream is = loader.getResourceAsStream("test.properties");
        pros.load(is);
        //此时文件应在src下

    }


    @Test
    public void test2(){
        try {



            Class clazz = Person.class;
            Object obj = clazz.newInstance();
            //内部调用了空参构造器
            //要想此方法正常与运行需要：
            //1，必须有空参构造器
            //2，空参构造器要有访问权限
            //javabean要求提供一个public的空参构造器
            //原因：便于通过反射创建运行时类的对象
            //      便于子类继承时调用super()，保证父类有此构造器
            System.out.println(obj);



        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void test3(){
        int num = new Random().nextInt(3);
        String classPath = "";
        switch(num){
            case 0:
                classPath = "java.util.Date";
                break;
            case 1:
                classPath = "java.sql.Date";
            case 2:
                classPath = "day24.Person";
                break;

        }

        try {
            Object obj = getInstance(classPath);
            System.out.println(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public Object getInstance(String classPath) throws Exception{
        Class clazz = Class.forName(classPath);
        return clazz.newInstance();
    }

    @Test
    public void test4(){
        //获取当前运行时类的属性结构

        Class clazz = Person.class;
        Field[] fields = clazz.getFields();
        //只能获取public的属性，包含父类属性
        for(Field f : fields){
            System.out.println(f);
        }

        fields = clazz.getDeclaredFields();
        //可以获取所有属性，不包含父类中声明的属性
        for(Field f : fields){
            System.out.println(f);
        }

    }


    @Test
    public void test5(){
        Class clazz = Person.class;
        Field[] decalardFields = clazz.getDeclaredFields();
        for(Field f: decalardFields){
            //获取权限修饰符
            int modifier = f.getModifiers();
            System.out.println(modifier);
            System.out.println(Modifier.toString(modifier));
            //数据类型

            Class type = f.getType();
            System.out.println(type.getName());

            //变量名
            System.out.println(f.getName());

        }
    }


    @Test
    public void test6(){
        Class clazz = Person.class;
        //获取当前类及父类中的public方法
        Method[] methods = clazz.getMethods();
        for(Method m : methods){
            System.out.println(m.getName());
            //权限修饰符
            int mo = m.getModifiers();

            //返回值类型
            Class reru = m.getReturnType();

            //注解
            Annotation[] ann = m.getAnnotations();
            //方法名

            m.getName();

            //形参列表

            Class[] cc = m.getParameterTypes();

            //抛出异常

            cc = m.getExceptionTypes();
            if(cc == null && cc.length == 0){

            }

        }

        //获取当前类中定义的所有方法，不包括父类方法
        Method[] decMethods = clazz.getDeclaredMethods();

    }


    @Test
    public void test7() {
        Class clazz = Person.class;
        //获取当前类中的public构造方法
        Constructor[] cons = clazz.getConstructors();
        //获取当前类中所有构造方法
        cons = clazz.getDeclaredConstructors();
        //
        Class superClass = clazz.getSuperclass();
        //获取带泛型的父类
        Type genericSuperclass = clazz.getGenericSuperclass();

        ParameterizedType py = (ParameterizedType)genericSuperclass;
        //获取泛型参数
        Type[] types = py.getActualTypeArguments();


        Class[] cls = clazz.getInterfaces();

        Package p = cls[0].getPackage();
        Annotation an[] = clazz.getAnnotations();


    }


    @Test
    public void test8() throws Exception{
        Class clazz = Person.class;

        Field id = clazz.getField("id");//该方法要求属性设置为public
        Person p = (Person)clazz.newInstance();

        id.set(p,1001);//参数一指明对象，参数二指明属性设置多少
        int pid = (Integer)id.get(p);//获取对象的当前属性值
        System.out.println(pid);
        //获取运行时类中指定变量的属性
        id = clazz.getDeclaredField("name");
        id.setAccessible(true);
        id.set(p,"zhangsan");
        String name = (String)id.get(p);
        System.out.println(name);



    }


    @Test
    public void test9() throws Exception {
        Class clazz = Person.class;
        Person p = (Person)clazz.newInstance();
        Method m = clazz.getDeclaredMethod("show",String.class);
        /*
        参数一：方法的调用者，参数二：方法实参
         */
        m.setAccessible(true);
        String s = (String)m.invoke(p,"test");
        //调用静态方法
        m = clazz.getDeclaredMethod("showId");
        m.setAccessible(true);
        //静态方法/属性的调用都是可以用类.class和null来实现
        Object re = m.invoke(Person.class);
        Object re1 = m.invoke(null);




    }

    @Test
    public void test10() throws Exception {
        Class clazz = Person.class;
        Person p = (Person) clazz.newInstance();
        Constructor cons = clazz.getDeclaredConstructor(String.class,int.class,int.class);
        cons.setAccessible(true);
        Person p1 = (Person)cons.newInstance("zhangsan",1,1);
        System.out.println(p1);
    }
}


class Creature<T> implements Serializable{

    private char gender;
    public double weight;

    public void breath(){
        System.out.println("creature breath");
    }

    public void eat(){
        System.out.println("creature eat");
    }

}

interface MyInterface{
    void info();
}


@Target({TYPE, FIELD, METHOD, PARAMETER, CONSTRUCTOR, LOCAL_VARIABLE})
@Retention(RetentionPolicy.RUNTIME)//运行时才可以被反射获取
@interface MyAnno{
    String value() default "hello";
}


@MyAnno(value="hi")
class Person extends Creature<String> implements MyInterface, Comparable{

    private String name;
    int age;
    public int id;

    public Person() {

    }

    public Person(int id) {
        this.id = id;
    }

    @MyAnno
    public Person(String name, int age, int id) {
        this.name = name;
        this.age = age;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", id=" + id +
                '}';
    }

    @Override
    public void info() {

    }

    public String display(String interests){
        return interests;
    }



    @Override
    public int compareTo(Object o) {
        return 0;
    }

    private String show(String nation){
        System.out.println("my nation is" + nation);
        return nation;
    }

    private static void showId(){
        System.out.println("static show id");
    }
}
