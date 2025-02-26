package com.github.bruce_mig.zimra_fdms_mock_server_java.util.swagger;


import java.util.Arrays;
import java.util.stream.Collectors;

import com.github.bruce_mig.zimra_fdms_mock_server_java.dto.UserSearchCriteria;
import com.github.bruce_mig.zimra_fdms_mock_server_java.dto.v1.device.Operator;
import com.github.bruce_mig.zimra_fdms_mock_server_java.dto.v1.device.Order;
import com.github.bruce_mig.zimra_fdms_mock_server_java.dto.v1.user.GetUsersListEnum;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.parameters.Parameter;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;

@Component
public class UserSearchCriteriaCustomizer implements OperationCustomizer {

    @Override
    public Operation customize(Operation operation, HandlerMethod handlerMethod) {
        // Remove any auto-generated query parameter representing the entire UserSearchCriteria object.
        if (operation.getParameters() != null) {
            operation.setParameters(
                    operation.getParameters().stream()
                            .filter(p -> !isUserSearchCriteriaParameter(p))
                            .collect(Collectors.toList())
            );
        }

        // Look for a method parameter of type UserSearchCriteria.
        for (MethodParameter methodParameter : handlerMethod.getMethodParameters()) {
            if (UserSearchCriteria.class.equals(methodParameter.getParameterType())) {
                // Create individual query parameters.
                Parameter sortParam = new Parameter()
                        .name("sort")
                        .in("query")
                        .description("Sort criteria")
                        .schema(new StringSchema()._enum(
                                Arrays.stream(GetUsersListEnum.values())
                                        .map(Enum::name)
                                        .collect(Collectors.toList())
                        ));

                Parameter orderParam = new Parameter()
                        .name("order")
                        .in("query")
                        .description("Order criteria")
                        .schema(new StringSchema()._enum(
                                Arrays.stream(Order.values())
                                        .map(Enum::name)
                                        .collect(Collectors.toList())
                        ));

                Parameter operatorParam = new Parameter()
                        .name("operator")
                        .in("query")
                        .description("Operator criteria")
                        .schema(new StringSchema()._enum(
                                Arrays.stream(Operator.values())
                                        .map(Enum::name)
                                        .collect(Collectors.toList())
                        ));

                // Add the new query parameters to the operation.
                operation.addParametersItem(sortParam);
                operation.addParametersItem(orderParam);
                operation.addParametersItem(operatorParam);

                // Only one UserSearchCriteria parameter is expected per method.
                break;
            }
        }

        return operation;
    }

    /**
     * Determines if the given parameter represents the auto-generated query parameter
     * for the UserSearchCriteria object.
     */
    private boolean isUserSearchCriteriaParameter(Parameter parameter) {
        // Check if the parameter name is "criteria" and is in the query.
        if ("criteria".equalsIgnoreCase(parameter.getName()) && "query".equalsIgnoreCase(parameter.getIn())) {
            return true;
        }
        // Alternatively, inspect the schema to see if it matches the expected object.
        Schema<?> schema = parameter.getSchema();
        if (schema != null && "object".equalsIgnoreCase(schema.getType()) && schema.getProperties() != null) {
            return schema.getProperties().containsKey("sort") &&
                    schema.getProperties().containsKey("order") &&
                    schema.getProperties().containsKey("operator");
        }
        return false;
    }
}
