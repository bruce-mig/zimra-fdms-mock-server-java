package com.github.bruce_mig.zimra_fdms_mock_server_java.dto.v1.device;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * SignatureData structure with SHA256 hash of fiscal day report fields (hash used for signature)
 * and fiscal day report device signature prepared by using device private key.
 * Validation rules:
 * fiscalDayDeviceSignature must be valid
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FiscalDayDeviceSignature {
    @NotNull
    @Size(max = 32)
    private String hash;

    @NotNull
    @Size(max = 256)
    private String signature;
}
