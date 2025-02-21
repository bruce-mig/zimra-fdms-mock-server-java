package com.github.bruce_mig.zimra_fdms_mock_server_java.service;

import com.github.bruce_mig.zimra_fdms_mock_server_java.dto.DeviceInfo;
import com.github.bruce_mig.zimra_fdms_mock_server_java.dto.v1.public_dto.*;


public interface PublicService {
    RegisterDeviceResponse registerDevice(Integer deviceID,DeviceInfo deviceInfo,RegisterDeviceRequest registerDeviceRequest);
    GetServerCertificateResponse getServerCertificate(String thumbprint);
    VerifyTaxpayerInformationResponse verifyTaxpayerInformation(Integer deviceID, VerifyTaxpayerInformationRequest request);
}
