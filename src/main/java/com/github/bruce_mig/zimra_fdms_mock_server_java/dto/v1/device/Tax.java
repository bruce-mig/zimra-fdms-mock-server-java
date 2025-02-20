package com.github.bruce_mig.zimra_fdms_mock_server_java.dto.v1.device;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Tax {
    private Double taxPercent;

    @NotBlank
    @Size(max = 50)
    private String taxName;

    @NotNull
    private LocalDateTime validFrom;

    private LocalDateTime validTill;

    /** Tax ID uniquely identifying a tax.
     * This tax ID must be used in submitting invoices.
     */
    @NotNull
    private Integer taxID;
}
