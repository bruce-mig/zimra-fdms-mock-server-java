package com.github.bruce_mig.zimra_fdms_mock_server_java.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Custom Annotation to validate deviceID with the ZIMRA Fiscal Backend
 * @author Bruce Migeri
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ValidateFiscalDevice {
}
