package cn.bricklayer.utils.exporter;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface ColumnDescription {

    String columnName() default "";

    int order() default -1;
}