package com.github.bruce_mig.zimra_fdms_mock_server_java.dto.v1.device;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Certificate signing request (CSR) for which certificate will be generated (in PEM format).
 * certificateRequest requirements are specified in registerDevice endpoint description.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IssueCertificateRequest {
    /**
     * example:
     * -----BEGIN CERTIFICATE REQUEST-----
     * MIHYMIGAAgEAMB4xHDAaBgNVBAMME1pSQi1lVkZELTAwMDAwMDAwNDIwWTATBgcq
     * hkjOPQIBBggqhkjOPQMBBwNCAAT7v3DvY7pRg4lz2Z87wSMwSX27KwlpYnSRV6WU
     * iPjpq2XsUAbg2lhUN7q3mlNJaUzqoKPmop5qURIpqUydXfapoAAwCgYIKoZIzj0E
     * AwIDRwAwRAIgLMEJQDh18bUE9waT2UXzP0+8FcGukpcIegMxd1A4JaQCIAZkzmEH
     * e0aaZ2jIcZArZo+rWzI4IwnSXtJqXLrpGUML
     * -----END CERTIFICATE REQUEST-----
     * */
    @NotBlank
    @Size(min = 1)
    private String certificateRequest;
}