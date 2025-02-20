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
public class VerifyTaxpayerInformationRequest {

    /**
     * Case insensitive 8 symbols key
     * example: 12AXC178
     */
    @NotBlank
    @Size(min = 1,max = 8)
    private String activationKey;

    /**
     * Device serial number assigned by manufacturer.
     * example: SN-001
     */
    @NotBlank
    @Size(min = 1,max = 20)
    private String deviceSerialNo;
}
