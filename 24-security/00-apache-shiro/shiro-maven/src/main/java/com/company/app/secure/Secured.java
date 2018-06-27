package com.company.app.secure;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@JacksonAnnotationsInside
@Target(value = ElementType.FIELD)
public @interface Secured {

}
