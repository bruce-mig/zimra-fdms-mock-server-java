package com.github.bruce_mig.zimra_fdms_mock_server_java.util.swagger;

import com.github.bruce_mig.zimra_fdms_mock_server_java.dto.DeviceSearchCriteria;
import com.github.bruce_mig.zimra_fdms_mock_server_java.dto.v1.device.DeviceSort;
import com.github.bruce_mig.zimra_fdms_mock_server_java.dto.v1.device.Operator;
import com.github.bruce_mig.zimra_fdms_mock_server_java.dto.v1.device.Order;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.parameters.Parameter;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;

import java.util.Arrays;
import java.util.stream.Collectors;

@Component
public class DeviceSearchCriteriaCustomizer implements OperationCustomizer {

    @Override
    public Operation customize(Operation operation, HandlerMethod handlerMethod) {
        // Remove any parameter that represents the entire DeviceSearchCriteria query object.
        if (operation.getParameters() != null) {
            operation.setParameters(
                    operation.getParameters().stream()
                            .filter(p -> !isDeviceSearchCriteriaParameter(p))
                            .collect(Collectors.toList())
            );
        }

        //  Method lookup for a parameter of type DeviceSearchCriteria.
        for (MethodParameter methodParameter : handlerMethod.getMethodParameters()) {
            if (DeviceSearchCriteria.class.equals(methodParameter.getParameterType())) {
                // Create individual query parameters for each field.

                Parameter operationIDParam = new Parameter()
                        .name("OperationID")
                        .in("query")
                        .description("Operation ID")
                        .schema(new StringSchema());

                Parameter fileUploadedFromParam = new Parameter()
                        .name("FileUploadedFrom")
                        .in("query")
                        .description("File upload from date-time")
                        .schema(new StringSchema().format("date-time"));

                Parameter fileUploadedTillParam = new Parameter()
                        .name("FileUploadedTill")
                        .in("query")
                        .description("File upload till date-time")
                        .schema(new StringSchema().format("date-time"));

                // For enum fields, set allowed values so Swagger UI renders a drop-down.
                Parameter deviceSortParam = new Parameter()
                        .name("DeviceSort")
                        .in("query")
                        .description("Sort criteria")
                        .schema(new StringSchema()._enum(
                                Arrays.stream(DeviceSort.values())
                                        .map(Enum::name)
                                        .collect(Collectors.toList())
                        ));

                Parameter orderParam = new Parameter()
                        .name("Order")
                        .in("query")
                        .description("Order criteria")
                        .schema(new StringSchema()._enum(
                                Arrays.stream(Order.values())
                                        .map(Enum::name)
                                        .collect(Collectors.toList())
                        ));

                Parameter operatorParam = new Parameter()
                        .name("Operator")
                        .in("query")
                        .description("Operator criteria")
                        .schema(new StringSchema()._enum(
                                Arrays.stream(Operator.values())
                                        .map(Enum::name)
                                        .collect(Collectors.toList())
                        ));

                // Add the new query parameters.
                operation.addParametersItem(operationIDParam);
                operation.addParametersItem(fileUploadedFromParam);
                operation.addParametersItem(fileUploadedTillParam);
                operation.addParametersItem(deviceSortParam);
                operation.addParametersItem(orderParam);
                operation.addParametersItem(operatorParam);

                break;
            }
        }
        return operation;
    }

    /**
     * Determines if the given parameter appears to be the auto-generated query representation
     * of the DeviceSearchCriteria object.
     */
    private boolean isDeviceSearchCriteriaParameter(Parameter parameter) {
        // Check by name ("criteria" as used in your method) and location.
        if ("criteria".equalsIgnoreCase(parameter.getName()) && "query".equalsIgnoreCase(parameter.getIn())) {
            return true;
        }
        // Alternatively, check if the parameter's schema is an object containing the expected fields.
        Schema<?> schema = parameter.getSchema();
        if (schema != null && "object".equalsIgnoreCase(schema.getType()) && schema.getProperties() != null) {
            return schema.getProperties().containsKey("operationID") &&
                    schema.getProperties().containsKey("fileUploadFrom") &&
                    schema.getProperties().containsKey("fileUploadTill") &&
                    schema.getProperties().containsKey("sort") &&
                    schema.getProperties().containsKey("order") &&
                    schema.getProperties().containsKey("operator");
        }
        return false;
    }
}
