package com.github.bruce_mig.zimra_fdms_mock_server_java.dto.v1.device;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
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
public class Receipt {

    @NotNull
    private ReceiptType receiptType;

    @NotBlank
    @Size(max = 3)
    private String receiptCurrency;

    @NotNull
    private Integer receiptCounter;

    @NotNull
    private Integer receiptGlobalNo;

    @NotBlank
    @Size(max = 50)
    private String invoiceNo;

    private BuyerData buyerData;
    private String receiptNotes;

    @NotNull
    private LocalDateTime receiptDate;
    private CreditDebitNote creditDebitNote;

    @NotNull
    private Boolean receiptLinesTaxInclusive;

    @NotEmpty
    private List<ReceiptLine> receiptLines;

    @NotEmpty
    private List<ReceiptTax> receiptTaxes;

    @NotEmpty
    private List<Payment> receiptPayments;

    @NotNull
    private Double receiptTotal;

    private ReceiptPrintForm receiptPrintForm;

    @NotNull
    private ReceiptDeviceSignature receiptDeviceSignature;
}
