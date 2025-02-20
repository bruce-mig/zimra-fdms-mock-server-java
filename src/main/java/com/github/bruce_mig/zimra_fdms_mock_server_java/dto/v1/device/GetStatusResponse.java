package com.github.bruce_mig.zimra_fdms_mock_server_java.dto.v1.device;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetStatusResponse {

    /**
     * Operation ID assigned by Fiscalisation Backend.
     * example: 0HMPH9AF0QKKE:00000005
     */
    @NotBlank
    @Size(max = 60)
    private String operationID;

    @NotNull
    private FiscalDayStatus fiscalDayStatus;

    private FiscalDayReconciliationMode fiscalDayReconciliationMode;
    private FiscalDayServerSignature fiscalDayServerSignature;
    private LocalDateTime fiscalDayClosed;
    private List<FiscalDayCounter> fiscalDayCounter;
    private Integer lastReceiptGlobalNo;
    private Integer lastFiscalDayNo;
    private FiscalDayClosingErrorCode fiscalDayClosingErrorCode;

    /** List of fiscal day document quantities.
     * This field is returned only when fiscalDayStatus is �FiscalDayClosed� and fiscalDayReconciliationMode is �Manual�.
     * FiscalDayDocumentQuantity type description provided in FiscalDayDocumentQuantity table.
     */
    private List<FiscalDayDocumentQuantity> fiscalDayDocumentQuantities;
}
