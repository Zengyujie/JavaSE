package day20;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;

@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({TYPE_PARAMETER, TYPE_USE, TYPE, FIELD, METHOD, PARAMETER, CONSTRUCTOR, LOCAL_VARIABLE})
public @interface MyAnnotations {
    MyAnnotation[] value();
}
