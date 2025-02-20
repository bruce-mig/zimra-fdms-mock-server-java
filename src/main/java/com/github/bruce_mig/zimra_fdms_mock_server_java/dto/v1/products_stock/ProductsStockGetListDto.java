package com.github.bruce_mig.zimra_fdms_mock_server_java.dto.v1.products_stock;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductsStockGetListDto {

    @NotBlank
    @Size(min = 1, max = 8)
    private String hsCode;

    @NotBlank
    @Size(min = 1, max = 200)
    private String goodName;

    @NotNull
    private Double quantity;

    @NotNull
    private Integer taxPayerId;

    @NotBlank
    @Size(min = 1)
    private String taxPayerTIN;

    @NotBlank
    @Size(min = 1, max = 250)
    private String taxPayerName;
    private Integer branchId;
    private String branchName;
}
