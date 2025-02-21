package com.github.bruce_mig.zimra_fdms_mock_server_java.dao;

import com.github.bruce_mig.zimra_fdms_mock_server_java.dto.v1.products_stock.ProductsStockGetListDto;
import com.github.bruce_mig.zimra_fdms_mock_server_java.dto.v1.products_stock.ProductsStockGetListDtoResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductsStockDAO {

    public ProductsStockGetListDtoResponse getProductsStockGetListDtoResponse() {
        List<ProductsStockGetListDto> rows = List.of(
                ProductsStockGetListDto.builder()
                        .hsCode("10R")
                        .goodName("Product 1")
                        .quantity(10.0)
                        .taxPayerId(45)
                        .taxPayerTIN("737")
                        .taxPayerName("Tax Payer 1")
                        .branchId(56)
                        .branchName("Branch 1")
                        .build()
        );

        return ProductsStockGetListDtoResponse.builder()
                .total(1)
                .rows(rows)
                .build();
    }
}
