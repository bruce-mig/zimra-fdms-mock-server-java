package com.github.bruce_mig.zimra_fdms_mock_server_java.dto.v1.public_dto;

import com.github.bruce_mig.zimra_fdms_mock_server_java.dto.v1.device.DeviceBranchAddress;
import com.github.bruce_mig.zimra_fdms_mock_server_java.dto.v1.device.DeviceBranchContacts;
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
public class VerifyTaxpayerInformationResponse {

    /**
     * Operation ID assigned by Fiscalisation Backend.
     * example: 0HMPH9AF0QKKE:00000005
     */
    @NotBlank
    @Size(max = 60)
    private String operationID;

    @NotBlank
    @Size(min = 1,max = 250)
    private String taxPayerName;

    @NotBlank
    @Size(min = 1,max = 10)
    private String taxPayerTIN;

    private String vatNumber;

    @NotBlank
    @Size(min = 1,max = 250)
    private String deviceBranchName;

    @NotNull
    private DeviceBranchAddress deviceBranchAddress;

    @NotNull
    private DeviceBranchContacts deviceBranchContacts;
}
