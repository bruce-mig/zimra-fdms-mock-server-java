package com.github.bruce_mig.zimra_fdms_mock_server_java.config;

import com.github.bruce_mig.zimra_fdms_mock_server_java.annotations.DeviceInfoHeader;
import com.github.bruce_mig.zimra_fdms_mock_server_java.dto.DeviceInfo;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.MethodParameter;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class DeviceInfoArgumentResolver implements HandlerMethodArgumentResolver {

    public static final String DEVICE_MODEL_NAME = "DeviceModelName";
    public static final String DEVICE_MODEL_VERSION = "DeviceModelVersion";

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(DeviceInfoHeader.class)
                && DeviceInfo.class.isAssignableFrom(parameter.getParameterType());
    }

    @Override
    public Object resolveArgument(@NonNull MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);

        if (request == null) {
            throw new IllegalStateException("HttpServletRequest is not available");
        }

        String deviceModelName = request.getHeader(DEVICE_MODEL_NAME);
        String deviceModelVersion = request.getHeader(DEVICE_MODEL_VERSION);

        if (deviceModelName == null || deviceModelVersion == null) {
            throw new MissingRequestHeaderException("DeviceModelName or DeviceModelVersion", parameter);
        }

        return new DeviceInfo(deviceModelName, deviceModelVersion);
    }
}
