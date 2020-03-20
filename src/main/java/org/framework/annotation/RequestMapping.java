package org.framework.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.TYPE})//方法和类
public @interface RequestMapping {
    String value();//路由
    HttpMethod method() default HttpMethod.GET;//指定的请求方法
}
