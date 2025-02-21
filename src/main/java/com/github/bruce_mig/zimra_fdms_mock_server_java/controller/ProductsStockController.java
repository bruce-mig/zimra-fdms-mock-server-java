package com.github.bruce_mig.zimra_fdms_mock_server_java.controller;

import com.github.bruce_mig.zimra_fdms_mock_server_java.annotations.DeviceInfoHeader;
import com.github.bruce_mig.zimra_fdms_mock_server_java.dto.DeviceInfo;
import com.github.bruce_mig.zimra_fdms_mock_server_java.dto.v1.products_stock.ProductSort;
import com.github.bruce_mig.zimra_fdms_mock_server_java.dto.ProductsSearchCriteria;
import com.github.bruce_mig.zimra_fdms_mock_server_java.dto.v1.device.Operator;
import com.github.bruce_mig.zimra_fdms_mock_server_java.dto.v1.device.Order;
import com.github.bruce_mig.zimra_fdms_mock_server_java.dto.v1.products_stock.ProductsStockGetListDtoResponse;
import com.github.bruce_mig.zimra_fdms_mock_server_java.util.OperationIDCache;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

/**
 * Goods Management
 */
@RestController
@CrossOrigin
@Validated
@Slf4j
@RequestMapping("/ProductsStock/v1")
public class ProductsStockController {

    public static final String OPERATION_ID = "operationId";
    private final OperationIDCache idCache;

    public ProductsStockController(OperationIDCache idCache) {
        this.idCache = idCache;
    }

    /**
     *  Gets warehouse product quantities list
     * @param deviceID
     * @param deviceInfo
     * @param offset
     * @param limit
     * @param criteria
     * @param bindingResult
     * @return ProductsStockGetListDtoResponse
     */
    @GetMapping("/{deviceID}/Search")
    public ResponseEntity<?> search(@PathVariable Integer deviceID,
                                                    @DeviceInfoHeader DeviceInfo deviceInfo,
                                                    @RequestParam("Offset") Integer offset,
                                                    @RequestParam("Limit") Integer limit,
                                                    @Valid @ModelAttribute ProductsSearchCriteria criteria,
                                                    BindingResult bindingResult){

        String operationID = idCache.getOperationID();
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set(OPERATION_ID,operationID);

        // Check for validation errors on the DeviceSearchCriteria object
        if (bindingResult.hasErrors()) {
            // Handle errors – for example, return a 400 Bad Request with error details
            return ResponseEntity.badRequest().headers(responseHeaders).body(bindingResult.getAllErrors());
        }


        return ResponseEntity.ok().headers(responseHeaders).body(new ProductsStockGetListDtoResponse());

    }

    /**  The @InitBinder method customizes the binding for ProductsSearchCriteria. */
    @InitBinder
    public void initBinder(WebDataBinder binder, WebRequest request) {
        Object target = binder.getTarget();
        if (target instanceof ProductsSearchCriteria criteria) {

            String hsCode = request.getParameter("HsCode");
            if (hsCode != null) {
                criteria.setHsCode(hsCode);
            }

            String goodName = request.getParameter("GoodName");
            if (goodName != null) {
                criteria.setGoodName(goodName);
            }

            String sort = request.getParameter("DeviceSort");
            if (sort != null) {
                criteria.setSort(ProductSort.valueOf(sort));
            }

            String order = request.getParameter("Order");
            if (order != null) {
                criteria.setOrder(Order.valueOf(order));
            }

            String operator = request.getParameter("Operator");
            if (operator != null) {
                criteria.setOperator(Operator.valueOf(operator));
            }
        }
    }
}
