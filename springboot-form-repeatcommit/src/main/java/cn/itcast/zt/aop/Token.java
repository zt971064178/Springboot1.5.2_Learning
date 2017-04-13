package cn.itcast.zt.aop;

import java.lang.annotation.*;

/**
 * Token注解类
 * Created by zhangtian on 2017/4/13.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface Token {
    boolean save() default false ;

    boolean remove() default false ;
}
