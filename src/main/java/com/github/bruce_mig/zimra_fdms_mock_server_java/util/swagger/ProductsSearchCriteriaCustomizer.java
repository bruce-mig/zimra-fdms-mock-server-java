package com.github.bruce_mig.zimra_fdms_mock_server_java.util.swagger;

import java.util.Arrays;
import java.util.stream.Collectors;

import com.github.bruce_mig.zimra_fdms_mock_server_java.dto.ProductsSearchCriteria;
import com.github.bruce_mig.zimra_fdms_mock_server_java.dto.v1.device.Operator;
import com.github.bruce_mig.zimra_fdms_mock_server_java.dto.v1.device.Order;
import com.github.bruce_mig.zimra_fdms_mock_server_java.dto.v1.products_stock.ProductSort;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.parameters.Parameter;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;


@Component
public class ProductsSearchCriteriaCustomizer implements OperationCustomizer {

    @Override
    public Operation customize(Operation operation, HandlerMethod handlerMethod) {
        // Remove the auto-generated query parameter for ProductsSearchCriteria, if it exists.
        if (operation.getParameters() != null) {
            operation.setParameters(
                    operation.getParameters().stream()
                            .filter(p -> !isProductsSearchCriteriaParameter(p))
                            .collect(Collectors.toList())
            );
        }

        // Find the method parameter of type ProductsSearchCriteria.
        for (MethodParameter methodParameter : handlerMethod.getMethodParameters()) {
            if (ProductsSearchCriteria.class.equals(methodParameter.getParameterType())) {
                // Add individual query parameters for each field.
                Parameter hsCodeParam = new Parameter()
                        .name("hsCode")
                        .in("query")
                        .description("Hs Code")
                        .schema(new StringSchema());

                Parameter goodNameParam = new Parameter()
                        .name("goodName")
                        .in("query")
                        .description("Good Name")
                        .schema(new StringSchema());

                // For the enum fields, set the allowed values so Swagger UI shows a drop-down.
                Parameter sortParam = new Parameter()
                        .name("sort")
                        .in("query")
                        .description("Sort criteria")
                        .schema(new StringSchema()._enum(
                                Arrays.stream(ProductSort.values())
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

                // Add all individual parameters.
                operation.addParametersItem(hsCodeParam);
                operation.addParametersItem(goodNameParam);
                operation.addParametersItem(sortParam);
                operation.addParametersItem(orderParam);
                operation.addParametersItem(operatorParam);

                break;
            }
        }
        return operation;
    }

    /**
     * Checks if the given parameter appears to represent the auto-generated query parameter
     * for the ProductsSearchCriteria object.
     */
    private boolean isProductsSearchCriteriaParameter(Parameter parameter) {
        // Check if the parameter name is "criteria"  and is in the query.
        if ("criteria".equalsIgnoreCase(parameter.getName()) && "query".equalsIgnoreCase(parameter.getIn())) {
            return true;
        }
        Schema<?> schema = parameter.getSchema();
        if (schema != null && "object".equalsIgnoreCase(schema.getType()) && schema.getProperties() != null) {
            return schema.getProperties().containsKey("hsCode") &&
                    schema.getProperties().containsKey("goodName") &&
                    schema.getProperties().containsKey("sort") &&
                    schema.getProperties().containsKey("order") &&
                    schema.getProperties().containsKey("operator");
        }
        return false;
    }
}
