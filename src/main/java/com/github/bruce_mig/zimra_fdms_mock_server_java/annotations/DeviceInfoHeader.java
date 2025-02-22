package com.github.bruce_mig.zimra_fdms_mock_server_java.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Custom annotation to capture and bind 'DeviceModelName' and 'DeviceModelVersion' query parameters
 * The required attribute defaults to false
 * @author Bruce Migeri
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface DeviceInfoHeader {
    boolean required() default false;
}
