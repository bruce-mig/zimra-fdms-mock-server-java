package com.github.bruce_mig.zimra_fdms_mock_server_java.dto.v1.device;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** List of fiscal counters.
 * Zero value counters may not be submitted to Fiscalisation Backend. */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FiscalDayCounter {

    @NotNull
    private FiscalCounterType fiscalCounterType;

    @NotBlank
    @Size(min = 3, max = 3)
    private String fiscalCounterCurrency;

    private Double fiscalCounterTaxPercent;
    private Integer fiscalCounterTaxID;
    private FiscalCounterMoneyType fiscalCounterMoneyType;

    @NotNull
    private Double fiscalCounterValue;
}
