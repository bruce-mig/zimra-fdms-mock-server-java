package com.github.bruce_mig.zimra_fdms_mock_server_java.service;

import com.github.bruce_mig.zimra_fdms_mock_server_java.dao.PublicDAO;
import com.github.bruce_mig.zimra_fdms_mock_server_java.dto.DeviceInfo;
import com.github.bruce_mig.zimra_fdms_mock_server_java.dto.v1.public_dto.*;
import com.github.bruce_mig.zimra_fdms_mock_server_java.exceptions.DeviceCertificateExpiredException;
import com.github.bruce_mig.zimra_fdms_mock_server_java.exceptions.ThumbprintCertificateNotFoundException;
import com.github.bruce_mig.zimra_fdms_mock_server_java.exceptions.UnprocessableContentException;
import org.springframework.stereotype.Service;

@Service
public class PublicServiceImpl implements PublicService {

    private final PublicDAO publicDao;

    public PublicServiceImpl(PublicDAO publicDao) {
        this.publicDao = publicDao;
    }

    @Override
    public RegisterDeviceResponse registerDevice(Integer deviceID, DeviceInfo deviceInfo, RegisterDeviceRequest registerDeviceRequest) {

        switch (deviceID){
            case 900:
                // Mock expiration of Certificate Device
                throw new DeviceCertificateExpiredException("Device certificate expired");
            case 901:
                // Mock 422
                throw new UnprocessableContentException("Operation failed because of provided data or invalid object state in Fiscal backend");
        }

        return publicDao.getRegisterDeviceResponse();
    }

    @Override
    public GetServerCertificateResponse getServerCertificate(String thumbprint) {

        switch (thumbprint){
            case "abc":
                throw new ThumbprintCertificateNotFoundException("Certificate requested by thumbprint not found");
            case "def":
                // Mock 422
                throw new UnprocessableContentException("Operation failed because of provided data or invalid object state in Fiscal backend");
        }

        return publicDao.getServerCertificateResponse();
    }

    @Override
    public VerifyTaxpayerInformationResponse verifyTaxpayerInformation(Integer deviceID, VerifyTaxpayerInformationRequest request) {

        if (deviceID == 900) {
            throw new UnprocessableContentException("Operation failed because of provided data or invalid object state in Fiscal backend");
        }

        return publicDao.getVerifyTaxpayerInformationResponse();
    }
}
