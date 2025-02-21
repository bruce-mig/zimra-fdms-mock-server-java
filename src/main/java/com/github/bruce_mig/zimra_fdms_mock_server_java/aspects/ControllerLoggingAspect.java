package com.github.bruce_mig.zimra_fdms_mock_server_java.aspects;

import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Arrays;
import java.util.Map;

@Aspect
@Component
public class ControllerLoggingAspect {

    @Value("${spring.profiles.active:dev}")
    private String environment;

    private static final Logger log = LoggerFactory.getLogger("ControllerLogger");

    @Before("com.github.bruce_mig.zimra_fdms_mock_server_java.aspects.CommonPointCuts.restControllerRequestMapping()")
    public void logIncomingRequest(JoinPoint joinPoint) {
        // Retrieve the current HttpServletRequest
        ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attrs == null) return;
        HttpServletRequest request = attrs.getRequest();

        String httpMethod = request.getMethod();
        String requestURI = request.getRequestURI();
        String queryString = request.getQueryString();

        // Build a JSON-like string for query parameters
        Map<String, String[]> params = request.getParameterMap();
        StringBuilder queryParams = new StringBuilder("{");
        params.forEach((key, value) ->
                queryParams.append(String.format(" \"%s\": \"%s\",", key, (value.length > 1 ? Arrays.toString(value) : value[0])))
        );
        if (queryParams.length() > 1) {
            queryParams.setLength(queryParams.length() - 1); // remove trailing comma
        }
        queryParams.append(" }");

        // ANSI escape codes for custom coloring (e.g. green text)
        String defaultColor  = "\033[0;32m";
        String darkOrange  = "\033[38;2;255;140;0m";
        String resetColor = "\033[0m";

        String logMessage = String.format(
                defaultColor + "INFO [%s]: " + darkOrange + "Incoming request" + defaultColor + "\n" +
                        "    env: \"%s\"\n" +
                        "    method: \"%s\"\n" +
                        "    url: \"%s%s\"\n" +
                        "    query: %s" + resetColor,
                java.time.LocalDateTime.now(),
                environment,
                httpMethod,
                requestURI,
                (queryString != null ? "?" + queryString : ""),
                queryParams.toString()
        );

        log.info(logMessage);
    }



}
