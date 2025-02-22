package com.github.bruce_mig.zimra_fdms_mock_server_java.service;

import com.github.bruce_mig.zimra_fdms_mock_server_java.annotations.ValidateFiscalDevice;
import com.github.bruce_mig.zimra_fdms_mock_server_java.dao.DeviceDAO;
import com.github.bruce_mig.zimra_fdms_mock_server_java.dto.DeviceInfo;
import com.github.bruce_mig.zimra_fdms_mock_server_java.dto.DeviceSearchCriteria;
import com.github.bruce_mig.zimra_fdms_mock_server_java.dto.v1.device.*;
import com.github.bruce_mig.zimra_fdms_mock_server_java.exceptions.DeviceNotFoundException;
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
    @ValidateFiscalDevice
    public GetConfigResponse getConfig(Integer deviceID, DeviceInfo deviceInfo) {
        return deviceDao.getConfigResponse();
    }

    @Override
    @ValidateFiscalDevice
    public GetStatusResponse getStatus(Integer deviceID, DeviceInfo deviceInfo) {
        return deviceDao.getStatusResponse();
    }

    @Override
    @ValidateFiscalDevice
    public OpenDayResponse openDay(Integer deviceID, DeviceInfo deviceInfo, OpenDayRequest request) {
        return deviceDao.getOpenDayResponse();

    }

    @Override
    @ValidateFiscalDevice
    public CloseDayResponse closeDay(Integer deviceID, DeviceInfo deviceInfo, CloseDayRequest request) {
        return deviceDao.getCloseDayResponse();
    }

    @Override
    @ValidateFiscalDevice
    public IssueCertificateResponse issueCertificate(Integer deviceID, DeviceInfo deviceInfo, IssueCertificateRequest request) {
        return deviceDao.getIssueCertificateResponse();
    }

    @Override
    @ValidateFiscalDevice
    public SubmitReceiptResponse submitReceipt(Integer deviceID, DeviceInfo deviceInfo, SubmitReceiptRequest request) {
        return deviceDao.getSubmitReceiptResponse();
    }

    @Override
    @ValidateFiscalDevice
    public PingResponse ping(Integer deviceID, DeviceInfo deviceInfo) {
        return deviceDao.getPingResponse();
    }

    @Override
    @ValidateFiscalDevice
    public SubmitFileResponse submitFile(Integer deviceID, DeviceInfo deviceInfo, SubmitFileRequest request) {

        if (deviceID == 902){
            throw new DeviceNotFoundException("Operation failed because no device were found with provided device id");
        }

        return deviceDao.getSubmitFileResponse();
    }

    @Override
    @ValidateFiscalDevice
    public SubmittedFileHeaderDtoListResponse getSubmittedFileList(Integer deviceID, DeviceInfo deviceInfo, Integer offset, Integer limit, DeviceSearchCriteria criteria) {
        return deviceDao.getSubmittedFileHeaderDtoListResponse(deviceID);
    }

}
