package com.github.bruce_mig.zimra_fdms_mock_server_java.service;

import com.github.bruce_mig.zimra_fdms_mock_server_java.dao.DeviceDAO;
import com.github.bruce_mig.zimra_fdms_mock_server_java.dto.DeviceInfo;
import com.github.bruce_mig.zimra_fdms_mock_server_java.dto.DeviceSearchCriteria;
import com.github.bruce_mig.zimra_fdms_mock_server_java.dto.v1.device.*;
import com.github.bruce_mig.zimra_fdms_mock_server_java.exceptions.DeviceCertificateExpiredException;
import com.github.bruce_mig.zimra_fdms_mock_server_java.exceptions.DeviceNotFoundException;
import com.github.bruce_mig.zimra_fdms_mock_server_java.exceptions.UnprocessableContentException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DeviceServiceImpl implements DeviceService {

    private final DeviceDAO deviceDao;

    public DeviceServiceImpl(DeviceDAO deviceDao) {
        this.deviceDao = deviceDao;
    }

    @Override
    public GetConfigResponse getConfig(Integer deviceID, DeviceInfo deviceInfo) {

        switch (deviceID){
            case 900:
                // Mock expiration of Certificate Device
                throw new DeviceCertificateExpiredException("Device certificate expired");
            case 901:
                // Mock 422
                throw new UnprocessableContentException("Operation failed because of provided data or invalid object state in Fiscal backend");
        }

        return deviceDao.getConfigResponse();
    }



    @Override
    public GetStatusResponse getStatus(Integer deviceID, DeviceInfo deviceInfo) {

        switch (deviceID){
            case 900:
                // Mock expiration of Certificate Device
                throw new DeviceCertificateExpiredException("Device certificate expired");
            case 901:
                // Mock 422
                throw new UnprocessableContentException("Operation failed because of provided data or invalid object state in Fiscal backend");
        }

        return deviceDao.getStatusResponse();
    }

    @Override
    public OpenDayResponse openDay(Integer deviceID, DeviceInfo deviceInfo, OpenDayRequest request) {

        switch (deviceID){
            case 900:
                // Mock expiration of Certificate Device
                throw new DeviceCertificateExpiredException("Device certificate expired");
            case 901:
                // Mock 422
                throw new UnprocessableContentException("Operation failed because of provided data or invalid object state in Fiscal backend");
        }

        return deviceDao.getOpenDayResponse();

    }

    @Override
    public CloseDayResponse closeDay(Integer deviceID, DeviceInfo deviceInfo, CloseDayRequest request) {

        switch (deviceID){
            case 900:
                // Mock expiration of Certificate Device
                throw new DeviceCertificateExpiredException("Device certificate expired");
            case 901:
                // Mock 422
                throw new UnprocessableContentException("Operation failed because of provided data or invalid object state in Fiscal backend");
        }

        return deviceDao.getCloseDayResponse();
    }

    @Override
    public IssueCertificateResponse issueCertificate(Integer deviceID, DeviceInfo deviceInfo, IssueCertificateRequest request) {

        switch (deviceID){
            case 900:
                // Mock expiration of Certificate Device
                throw new DeviceCertificateExpiredException("Device certificate expired");
            case 901:
                // Mock 422
                throw new UnprocessableContentException("Operation failed because of provided data or invalid object state in Fiscal backend");
        }

        return deviceDao.getIssueCertificateResponse();
    }

    @Override
    public SubmitReceiptResponse submitReceipt(Integer deviceID, DeviceInfo deviceInfo, SubmitReceiptRequest request) {

        switch (deviceID){
            case 900:
                // Mock expiration of Certificate Device
                throw new DeviceCertificateExpiredException("Device certificate expired");
            case 901:
                // Mock 422
                throw new UnprocessableContentException("Operation failed because of provided data or invalid object state in Fiscal backend");
        }

        return deviceDao.getSubmitReceiptResponse();
    }

    @Override
    public PingResponse ping(Integer deviceID, DeviceInfo deviceInfo) {

        switch (deviceID){
            case 900:
                // Mock expiration of Certificate Device
                throw new DeviceCertificateExpiredException("Device certificate expired");
            case 901:
                // Mock 422
                throw new UnprocessableContentException("Operation failed because of provided data or invalid object state in Fiscal backend");
        }

        return deviceDao.getPingResponse();
    }

    @Override
    public SubmitFileResponse submitFile(Integer deviceID, DeviceInfo deviceInfo, SubmitFileRequest request) {

        switch (deviceID){
            case 900:
                // Mock expiration of Certificate Device
                throw new DeviceCertificateExpiredException("Device certificate expired");
            case 901:
                // Mock 422
                throw new UnprocessableContentException("Operation failed because of provided data or invalid object state in Fiscal backend");
            case 902:
                throw new DeviceNotFoundException("Operation failed because no device were found with provided device id");
        }

        return deviceDao.getSubmitFileResponse();
    }

    @Override
    public SubmittedFileHeaderDtoListResponse getSubmittedFileList(Integer deviceID, DeviceInfo deviceInfo, Integer offset, Integer limit, DeviceSearchCriteria criteria) {

        switch (deviceID){
            case 900:
                // Mock expiration of Certificate Device
                throw new DeviceCertificateExpiredException("Device certificate expired");
            case 901:
                // Mock 422
                throw new UnprocessableContentException("Operation failed because of provided data or invalid object state in Fiscal backend");
        }

        return deviceDao.getSubmittedFileHeaderDtoListResponse(deviceID);
    }

}
