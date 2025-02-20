package com.github.bruce_mig.zimra_fdms_mock_server_java.dto.v1.public_dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDeviceRequest {
    /**
     * Certificate signing request (CSR) for which certificate will be generated (in PEM format).
     * Assigned by Tax Authority device name (format: [CLIENT]-[Fiscal_device_serial_no]-[zero_padded_10_digit_deviceId]) should be provided in CSR`s Subject CN.
     * Supported algorithms and key types (in order of suggested preference):
     * 1. ECC ECDSA on SECG secp256r1 curve (also named as ANSI prime256v1, NIST P-256); Signature Algorithm: ecdsa-with-SHA256.
     * 2.  RSA 2048; Signature Algorithm - SHA256WithRSA.
     * example:
     * -----BEGIN CERTIFICATE REQUEST-----
     * MIHYMIGAAgEAMB4xHDAaBgNVBAMME1pSQi1lVkZELTAwMDAwMDAwNDIwWTATBgcq
     * hkjOPQIBBggqhkjOPQMBBwNCAAT7v3DvY7pRg4lz2Z87wSMwSX27KwlpYnSRV6WU
     * iPjpq2XsUAbg2lhUN7q3mlNJaUzqoKPmop5qURIpqUydXfapoAAwCgYIKoZIzj0E
     * AwIDRwAwRAIgLMEJQDh18bUE9waT2UXzP0+8FcGukpcIegMxd1A4JaQCIAZkzmEH
     * e0aaZ2jIcZArZo+rWzI4IwnSXtJqXLrpGUML
     * -----END CERTIFICATE REQUEST-----
     */
    @NotBlank
    @Size(min = 1)
    private String certificateRequest;

    /**
     * Case insensitive 8 symbols key
     * example: 12AXC178
     */
    @NotBlank
    @Size(max = 8)
    private String activationKey;
}
