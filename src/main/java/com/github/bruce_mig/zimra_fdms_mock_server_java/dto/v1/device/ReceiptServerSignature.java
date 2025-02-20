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
public class ReceiptServerSignature {
    @NotNull
    @Size(max = 32)
    private byte[] hash;

    @NotNull
    @Size(max = 256)
    private byte[] signature;

    /**
     * SHA-1 Thumbprint of Certificate used for signature
     * example: b785a0b4d8a734a55ba595d143b4cf72834cd88d
     */
    @NotBlank
    @Size(min = 1)
    private String certificateThumbprint;
}
