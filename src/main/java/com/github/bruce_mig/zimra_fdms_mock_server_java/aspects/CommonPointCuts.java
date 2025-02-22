package com.github.bruce_mig.zimra_fdms_mock_server_java.aspects;

import org.aspectj.lang.annotation.Pointcut;

public class CommonPointCuts {

    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
    public void restController() {}

    @Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping) || " +
            "@annotation(org.springframework.web.bind.annotation.PostMapping) || " +
            "@annotation(org.springframework.web.bind.annotation.PutMapping) || " +
            "@annotation(org.springframework.web.bind.annotation.DeleteMapping) || " +
            "@annotation(org.springframework.web.bind.annotation.PatchMapping)")
    public void requestMappingAnnotations() {}

    @Pointcut("restController() && requestMappingAnnotations()")
    public void restControllerRequestMapping() {}

    @Pointcut("@annotation(com.github.bruce_mig.zimra_fdms_mock_server_java.annotations.ValidateFiscalDevice)")
    public void validateDeviceAnnotation() {}

    @Pointcut("args(deviceID,..)")
    public void deviceIDsArgs(Integer deviceID) {}

    @Pointcut(value = "validateDeviceAnnotation() && deviceIDsArgs(deviceID)", argNames = "deviceID")
    public void validateDevice(Integer deviceID) {}
}
