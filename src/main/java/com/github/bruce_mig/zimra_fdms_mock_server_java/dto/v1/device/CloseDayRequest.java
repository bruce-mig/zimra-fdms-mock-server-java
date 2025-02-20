package com.github.bruce_mig.zimra_fdms_mock_server_java.dto.v1.device;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CloseDayRequest {

    /** Fiscal day number. Validation rules:
     * fiscalDayNo must be the same as provided/received fiscalDayNo value in openDay request. */
    @NotNull
    private Integer fiscalDayNo;

    @NotBlank
    private List<FiscalDayCounter> fiscalDayCounters;

    @NotNull
    private FiscalDayDeviceSignature fiscalDayDeviceSignature;

    @NotNull
    private Integer receiptCounter;
}
