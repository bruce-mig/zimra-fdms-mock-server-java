package com.github.bruce_mig.zimra_fdms_mock_server_java.dto.v1.device;

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
public class ReceiptTax {

    @Size(max = 3)
    private String taxCode;
    private Double taxPercent;

    @NotNull
    private Integer taxID;

    @NotNull
    private Double taxAmount;

    @NotNull
    private Double salesAmountWithTax;
}
