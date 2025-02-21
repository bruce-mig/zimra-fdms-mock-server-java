package com.github.bruce_mig.zimra_fdms_mock_server_java.controller;

import com.github.bruce_mig.zimra_fdms_mock_server_java.annotations.DeviceInfoHeader;
import com.github.bruce_mig.zimra_fdms_mock_server_java.dto.DeviceInfo;
import com.github.bruce_mig.zimra_fdms_mock_server_java.dto.v1.public_dto.*;
import com.github.bruce_mig.zimra_fdms_mock_server_java.util.OperationIDCache;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Public endpoints do not require client certificate to authenticate
 */
@RestController
@CrossOrigin
@Validated
@Slf4j
@RequestMapping("/Public/v1")
public class PublicController {

    public static final String OPERATION_ID = "operationId";
    private final OperationIDCache idCache;

    public PublicController(OperationIDCache idCache) {
        this.idCache = idCache;
    }

    /**
     * Endpoint is used to get device certificate and register device in Fiscalisation Backend (link device with Fiscalisation Backend).
     * @param deviceID
     * @param deviceInfo
     * @param registerDeviceRequest
     * @return
     */
    @PostMapping("{deviceID}/RegisterDevice")
    public ResponseEntity<?> registerDevice(@PathVariable Integer deviceID,
                                            @DeviceInfoHeader DeviceInfo deviceInfo,
                                            @Valid @RequestBody RegisterDeviceRequest registerDeviceRequest) {
        String operationID = idCache.getOperationID();
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set(OPERATION_ID,operationID);

        if (deviceInfo.deviceModelName().isBlank() || deviceInfo.deviceModelVersion().isBlank()) {
            return ResponseEntity.badRequest().headers(responseHeaders).body("Missing required header(s): DeviceModelName or DeviceModelVersion");
        }

        log.debug("DeviceModelName: '{}' | DeviceModelVersion: '{}'", deviceInfo.deviceModelName(), deviceInfo.deviceModelVersion());

        RegisterDeviceResponse registerDeviceResponse = RegisterDeviceResponse.builder()
                .operationID(operationID)
                .build();

        return ResponseEntity.ok().headers(responseHeaders).body(registerDeviceResponse);


    }

    /**
     * Endpoint is used to retrieve Fiscalisation Backend certificate for Fiscalisation Backend signature validation.
     * Despite whether the parameter is provided or not, and despite what certificate in the chain thumbprint indicates (first, second or third level certificate),
     * full certificate chain is returned
     * @param thumbprint -Thumbprint of Fiscalisation Backend certificate which should be returned.
     * If field is not provided, currently active Fiscalisation Backend signing certificate is returned

     * Example : b785a0b4d8a734a55ba595d143b4cf72834cd88d
     * @return GetServerCertificateResponse
     */
    @GetMapping("/GetServerCertificate")
    public ResponseEntity<GetServerCertificateResponse> getServerCertificate(@RequestParam("thumbprint") String thumbprint) {

        String operationID = idCache.getOperationID();
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set(OPERATION_ID,operationID);

        GetServerCertificateResponse serverCertificateResponse = GetServerCertificateResponse.builder()
                .operationID(operationID)
                .build();

        return ResponseEntity.ok().headers(responseHeaders).body(serverCertificateResponse);
    }

    /**
     * Endpoint is used to retrieve taxpayer information from system before device registration
     * (in order user could double-check if device is going to be registered to correct taxpayer).
     * @param deviceID
     * @param request of type VerifyTaxpayerInformationRequest
     * @return VerifyTaxpayerInformationResponse
     */
    @PostMapping("/{deviceID}/VerifyTaxpayerInformation")
    public ResponseEntity<VerifyTaxpayerInformationResponse> verifyTaxpayerInformation(@PathVariable Integer deviceID,
                                                       @Valid @RequestBody VerifyTaxpayerInformationRequest request) {

        String operationID = idCache.getOperationID();
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set(OPERATION_ID,operationID);

        VerifyTaxpayerInformationResponse response = VerifyTaxpayerInformationResponse.builder()
                .operationID(operationID)
                .build();

        return ResponseEntity.ok().headers(responseHeaders).body(response);
    }
}
