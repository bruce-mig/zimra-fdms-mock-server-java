package com.github.bruce_mig.zimra_fdms_mock_server_java.controller;

import com.github.bruce_mig.zimra_fdms_mock_server_java.annotations.DeviceInfoHeader;
import com.github.bruce_mig.zimra_fdms_mock_server_java.dto.DeviceInfo;
import com.github.bruce_mig.zimra_fdms_mock_server_java.dto.OperationIdHeader;
import com.github.bruce_mig.zimra_fdms_mock_server_java.dto.v1.public_dto.*;
import com.github.bruce_mig.zimra_fdms_mock_server_java.service.PublicService;
import com.github.bruce_mig.zimra_fdms_mock_server_java.util.op_id.OperationIDCache;
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
    private final PublicService publicService;

    public PublicController(OperationIDCache idCache, PublicService publicService) {
        this.idCache = idCache;
        this.publicService = publicService;
    }

    /**
     * Endpoint is used to get device certificate and register device in Fiscalisation Backend (link device with Fiscalisation Backend).
     * @param deviceID
     * @param deviceInfo
     * @param registerDeviceRequest
     * @return RegisterDeviceResponse
     */
    @PostMapping("{deviceID}/RegisterDevice")
    public ResponseEntity<?> registerDevice(@PathVariable Integer deviceID,
                                            @DeviceInfoHeader(required = true) DeviceInfo deviceInfo,
                                            @Valid @RequestBody RegisterDeviceRequest registerDeviceRequest) {

        OperationIdHeader opIdHeader = createOpIdHeader();

        if (deviceInfo.deviceModelName().isBlank() || deviceInfo.deviceModelVersion().isBlank()) {
            return ResponseEntity.badRequest()
                    .headers(opIdHeader.headers())
                    .body("Missing required header(s): DeviceModelName or DeviceModelVersion");
        }

        RegisterDeviceResponse registerDeviceResponse = publicService.registerDevice(deviceID, deviceInfo, registerDeviceRequest);
        registerDeviceResponse.setOperationID(opIdHeader.operationID());

        return ResponseEntity.ok()
                .headers(opIdHeader.headers())
                .body(registerDeviceResponse);
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

        GetServerCertificateResponse response = publicService.getServerCertificate(thumbprint);
        OperationIdHeader opIdHeader = createOpIdHeader();
        response.setOperationID(opIdHeader.operationID());

        return ResponseEntity.ok()
                .headers(opIdHeader.headers())
                .body(response);
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

        VerifyTaxpayerInformationResponse response = publicService.verifyTaxpayerInformation(deviceID, request);
        OperationIdHeader opIdHeader = createOpIdHeader();
        response.setOperationID(opIdHeader.operationID());

        return ResponseEntity.ok().headers(opIdHeader.headers()).body(response);
    }

    // Helper method to create headers with operationID
    private OperationIdHeader createOpIdHeader() {
        String operationID = idCache.getOperationID();
        HttpHeaders headers = new HttpHeaders();
        headers.set(OPERATION_ID,operationID);

        return OperationIdHeader.builder()
                .operationID(operationID)
                .headers(headers)
                .build();

    }
}
