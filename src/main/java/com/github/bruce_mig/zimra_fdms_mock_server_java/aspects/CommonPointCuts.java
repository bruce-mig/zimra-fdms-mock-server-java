package com.github.bruce_mig.zimra_fdms_mock_server_java.aspects;

import org.aspectj.lang.annotation.Pointcut;

public class CommonPointCuts {

    // Limit to classes annotated with @RestController
    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
    public void restController() {}

    // Limit to methods annotated with any of the request mapping annotations
    @Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping) || " +
            "@annotation(org.springframework.web.bind.annotation.PostMapping) || " +
            "@annotation(org.springframework.web.bind.annotation.PutMapping) || " +
            "@annotation(org.springframework.web.bind.annotation.DeleteMapping) || " +
            "@annotation(org.springframework.web.bind.annotation.PatchMapping)")
    public void requestMappingAnnotations() {}

    // Combine both pointcuts so only the desired mapping methods are intercepted
    @Pointcut("restController() && requestMappingAnnotations()")
    public void restControllerRequestMapping() {}
}
