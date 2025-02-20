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
public class ReceiptLine {

    @NotNull
    private ReceiptLineType receiptLineType;

    @NotNull
    private Integer receiptLineNo;

    @Size(max = 8)
    private String receiptLineHSCode;

    @NotBlank
    @Size(max = 200)
    private String receiptLineName;

    private Double receiptLinePrice;

    @NotNull
    private Double receiptLineQuantity;

    @NotNull
    private Double receiptLineTotal;

    @Size(max = 3)
    private String taxCode;
    private Double taxPercent;

    @NotNull
    private Integer taxID;
}
