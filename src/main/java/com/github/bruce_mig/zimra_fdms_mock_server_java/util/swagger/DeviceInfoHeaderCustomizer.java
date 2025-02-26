package com.github.bruce_mig.zimra_fdms_mock_server_java.util.swagger;

import com.github.bruce_mig.zimra_fdms_mock_server_java.annotations.DeviceInfoHeader;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.parameters.Parameter;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;

import java.util.stream.Collectors;

/**
 * A Springdoc OperationCustomizer that detects any controller method parameter annotated with
 * {@code @DeviceInfoHeader} and replaces its API documentation representation (typically a query parameter)
 * with two header parameters: DeviceModelName and DeviceModelVersion.
 */
@Component
public class DeviceInfoHeaderCustomizer implements OperationCustomizer {

    @Override
    public Operation customize(Operation operation, HandlerMethod handlerMethod) {
        // Remove any parameters that appear to represent the DeviceInfo query parameter.
        if (operation.getParameters() != null) {
            operation.setParameters(
                    operation.getParameters().stream()
                            .filter(p -> !isDeviceInfoQueryParameter(p))
                            .collect(Collectors.toList())
            );
        }

        // Iterate over the method parameters to see if any is annotated with @DeviceInfoHeader.
        for (MethodParameter methodParameter : handlerMethod.getMethodParameters()) {
            if (methodParameter.hasParameterAnnotation(DeviceInfoHeader.class)) {
                DeviceInfoHeader annotation = methodParameter.getParameterAnnotation(DeviceInfoHeader.class);
                boolean required = annotation != null && annotation.required();

                // Define header parameter for DeviceModelName.
                Parameter deviceModelNameHeader = new Parameter()
                        .name("DeviceModelName")
                        .in("header")
                        .description("Device model name as registered in Tax Authority")
                        .required(required);

                // Define header parameter for DeviceModelVersion.
                Parameter deviceModelVersionHeader = new Parameter()
                        .name("DeviceModelVersion")
                        .in("header")
                        .description("Device model version number as registered in Tax Authority")
                        .required(required);

                // Add the header parameters to the operation.
                operation.addParametersItem(deviceModelNameHeader);
                operation.addParametersItem(deviceModelVersionHeader);

                break;
            }
        }
        return operation;
    }

    /**
     * Determines if the given parameter is likely the auto-generated query representation
     * of the DeviceInfo object.
     */
    private boolean isDeviceInfoQueryParameter(Parameter parameter) {
        // Check if the parameter is in the query.
        if (!"query".equalsIgnoreCase(parameter.getIn())) {
            return false;
        }

        // If the parameter name is "deviceInfo", it's very likely the object.
        if ("deviceInfo".equalsIgnoreCase(parameter.getName())) {
            return true;
        }

        Schema<?> schema = parameter.getSchema();
        if (schema != null) {
            // If the schema is a reference, check if it references DeviceInfo.
            if (schema.get$ref() != null && schema.get$ref().contains("DeviceInfo")) {
                return true;
            }
            // If the schema is inline, check if it's an object with the expected properties.
            if ("object".equalsIgnoreCase(schema.getType()) && schema.getProperties() != null) {
                return schema.getProperties().containsKey("deviceModelName") &&
                        schema.getProperties().containsKey("deviceModelVersion");
            }
        }
        return false;
    }
}