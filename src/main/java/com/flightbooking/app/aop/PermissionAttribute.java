package com.flightbooking.app.aop;

import java.lang.annotation.*;

@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PermissionAttribute {
    String value() default "";

    boolean required() default false;
}
