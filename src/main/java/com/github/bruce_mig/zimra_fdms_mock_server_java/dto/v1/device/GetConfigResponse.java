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
public class GetConfigResponse {

    /** Operation ID assigned by Fiscalisation Backend.
     * example: 0HMPH9AF0QKKE:00000005
     * */
    @NotBlank
    @Size(max = 60)
    private String operationID;

    @NotBlank
    @Size(max = 250)
    private String taxPayerName;

    @NotBlank
    @Size(min = 10,max = 10)
    private String taxPayerTIN;

    @Size(min = 9,max = 9)
    private String vatNumber;

    @NotBlank
    @Size(max = 20)
    private String deviceSerialNo;

    @NotBlank
    private String deviceBranchName;

    @NotNull
    private DeviceBranchAddress deviceBranchAddress;

    private DeviceBranchContacts deviceBranchContacts;

    @NotNull
    private DeviceOperatingMode deviceOperatingMode;

    @NotNull
    private Integer taxPayerDayMaxHrs;

    @NotEmpty
    private List<Tax> applicableTaxes;

    @NotNull
    private LocalDateTime certificateValidTill;

    @NotBlank
    @Size(max = 50)
    private String qrUrl;

    /** How much time in hours before end of fiscal day device should show notification to salesperson. */
    @NotNull
    private Integer taxpayerDayEndNotificationHrs;


}
