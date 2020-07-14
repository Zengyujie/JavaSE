package JUC_test;

public class LambdaTest1 {
    /*
    函数式编程
    1，Lambda表达式解决了匿名内部类代码冗余的问题
    2，lambda表达式写法:拷贝中括号(函数定义中的)，写死右箭头，落地大括号(函数体)
    3，用Lambda表达式的前提是接口是函数式接口，即接口中只有一个函数
    4，@FunctionalInterface注解，表示函数式接口，接口中只有一个函数
        函数式接口中可以有default方法和static方法(1.8新增)
    5，函数式接口中static方法的调用需要通过类名，而不是对象


     */

    public static void main(String[] args) {
        Foo foo = new Foo(){
            @Override
            public void sayHello() {
                System.out.println("test");
            }
        };
        foo.sayHello();

        Foo foo1 = ()->{System.out.println("test1");};
        foo1.sayHello();

        Foo1 f1 = (int a, int b)->{return a + b;};
        System.out.println(f1.add(1,2));
        Foo.sub(1,2);
    }

}

@FunctionalInterface
interface Foo{
    void sayHello();

    default void test(){
        System.out.println("test");
    }

    default void test1(){
        System.out.println("test1");
    }

    static int sub(int a, int b){
        return a - b;
    }
}

@FunctionalInterface
interface Foo1{
    int add(int a, int b);
}

