package com.github.bruce_mig.zimra_fdms_mock_server_java.dto.v1.public_dto;

import jakarta.validation.constraints.NotBlank;
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
public class GetServerCertificateResponse {

    /**
     * Operation ID assigned by Fiscalisation Backend.
     * example: 0HMPH9AF0QKKE:00000005
     */
    @NotBlank
    @Size(max = 60)
    private String operationID;

    /** Fiscalisation Backend certificate chain (according x.509 standard) to validate Fiscalisation Backend signatures. */
    private List<String> certificate;

    /**
     * Date till when Fiscalisation Backend signing certificate is valid (despite that in the certificate parameter all the certificate chain is returned,
     * this field shows validity time of the child certificate in the chain). Times is provided in UTC time.
     */
    private LocalDateTime certificateValidTill;
}
