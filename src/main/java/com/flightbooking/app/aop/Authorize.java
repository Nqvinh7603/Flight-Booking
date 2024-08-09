package com.flightbooking.app.aop;


import com.flightbooking.domain.auth.constant.Action;
import com.flightbooking.domain.auth.constant.Resource;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Authorize {

    Action action();

    Resource resource();
}
