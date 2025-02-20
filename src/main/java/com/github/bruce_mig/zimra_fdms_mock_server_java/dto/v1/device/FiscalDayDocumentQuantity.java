package com.github.bruce_mig.zimra_fdms_mock_server_java.dto.v1.device;

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
public class FiscalDayDocumentQuantity {
    @NotNull
    private String receiptType;

    /** Receipt counter currency (ISO 4217 currency code).
     * example: USD
     * */
    @NotBlank
    @Size(min = 3, max = 3)
    private String receiptCurrency;

    /** Total quantity of receipts of particular receipt type and currency for fiscal day. */
    @NotNull
    private Integer receiptQuantity;

    /** Total receipt amount (including tax) of receipts of particular receipt type and currency for fiscal day.
     * example: 3500.05
     * */
    @NotNull
    private Double receiptTotalAmount;
}
