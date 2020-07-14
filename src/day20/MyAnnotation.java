package day20;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;

@Repeatable(MyAnnotations.class)
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({TYPE_PARAMETER, TYPE_USE,TYPE, FIELD, METHOD, PARAMETER, CONSTRUCTOR, LOCAL_VARIABLE})
public @interface MyAnnotation {
    /*
    自定义注解：
    1，使用@interface关键字
    2，自动继承了java.lang.annotation.Annotation接口
    3，Annotation成员变量在Annotation定义中以无参数方式的形式来声明
        ，其方法名和返回值定义了该成员的名字和类型，成为配置参数，类型只能是以下
        八种：String，Class，enum，Annotation，以及它们的数组
    4，可以在成员变量定义时为其指定初始值，可以使用default关键字
    5，如果只有一个参数成员，建议名为value
    6，如果定义的注解含有配置参数，那么使用时必须指定参数值，除非有默认值
        格式是“参数名=参数值”，如果只有一个参数成员，且名称为value，可以省略"value="
    7，没有定义成员的Annotation成为标记，包含成员变量的Annotation成为元数据Annotation
    8，自定义注解必须配上注解的信息处理流程(和反射结合)才有意义


     */

    String value() default "test";

}
