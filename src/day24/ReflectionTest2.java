package day24;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ReflectionTest2 {

    /*
    代理模式的原理：
    1，使用一个代理将对象包装起来，然后用该代理对象取代原始对象，任何一个原始对象的调用
        都要通过代理，代理对象决定是否以及何时将方法调用转到原始对象上
    2，静态代理的特征是代理类和目标对象的类都是在编译时间确定下来，不利于程序的扩展，同时
        一个代理只能为一个接口服务，这样会产生多个代理
    3，动态代理指客户通过代理类类调用其他对象方法，并且是在程序运行时根据需要动态创建代理对象

     */
    public static void main(String[] args) {
        /*
        静态代理：代理类和被代理类在编译时就定下来了
         */
        //创建被代理对象
        Nike nike = new Nike();
        //创建代理对象
        ProxyClothFactory pcf = new ProxyClothFactory(nike);
        pcf.produceCloth();

        System.out.println("------------");
        /*
        动态代理：
         */

        SuperMan sm = new SuperMan();
        //pro是被代理类的对象
        Human pro = (Human)ProxyFac.getProxyInstance(sm);
        pro.getBelief();
        pro.eat("hotpot");
    }

}


interface ClothFactory{
    void produceCloth();
}

class ProxyClothFactory implements ClothFactory{

    private ClothFactory factory;
    public ProxyClothFactory(ClothFactory c){
        this.factory = c;
    }

    @Override
    public void produceCloth() {
        System.out.println("proxy factory start");
        factory.produceCloth();
        System.out.println("proxy factory end");
    }
}

class Nike implements ClothFactory{

    @Override
    public void produceCloth() {
        System.out.println("nike");
    }
}



///////////////////////////////////

/*
实现动态代理解决的问题：
1，如果根据加载到内存中的被代理类，动态创建一个代理对象
2，当通过代理类的对象调用方式如何动态调用被代理类的方法
3，


 */

interface Human{
    String getBelief();
    void eat(String food);
}

class SuperMan implements Human{


    @Override
    public String getBelief() {
        return "superman";
    }

    @Override
    public void eat(String food) {
        System.out.println("superman eat");
    }
}

class ProxyFac{
    //通过调用此方法返回一个被代理类对象
    public static Object getProxyInstance(Object obj){
        //obj:被代理的对象

        MyInvocationHandler mi = new MyInvocationHandler();
        mi.bind(obj);
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(),
                obj.getClass().getInterfaces(),mi
                );
    }
}

class MyInvocationHandler implements InvocationHandler{
    private Object obj;//赋值时需要使用被代理对象赋值

    public void bind(Object obj){
        this.obj = obj;
    }

    //通过代理类对象调用方法时，就会调用如下方法
    //将被代理类要执行的方法功能声明在invoke方法中
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        HumanUtil util = new HumanUtil();
        util.method1();
        //method为代理对象的方法，此方法也作为了被代理类对象要调用的方法
        //obj：被代理对象
        Object value = method.invoke(obj, args);
        //上述方法返回值作为invoke的返回值
        util.method2();
        return value;
    }
}


class HumanUtil{

    public void method1(){
        System.out.println("method1");
    }

    public void method2(){
        System.out.println("method2");
    }

}