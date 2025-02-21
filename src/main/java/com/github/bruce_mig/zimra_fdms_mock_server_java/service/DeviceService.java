package com.github.bruce_mig.zimra_fdms_mock_server_java.service;

import com.github.bruce_mig.zimra_fdms_mock_server_java.dto.DeviceInfo;
import com.github.bruce_mig.zimra_fdms_mock_server_java.dto.DeviceSearchCriteria;
import com.github.bruce_mig.zimra_fdms_mock_server_java.dto.v1.device.*;

public interface DeviceService {
    GetConfigResponse getConfig(Integer deviceID, DeviceInfo deviceInfo);
    GetStatusResponse getStatus(Integer deviceID, DeviceInfo deviceInfo);
    OpenDayResponse openDay(Integer deviceID, DeviceInfo deviceInfo, OpenDayRequest request);
    CloseDayResponse closeDay(Integer deviceID, DeviceInfo deviceInfo, CloseDayRequest request);
    IssueCertificateResponse issueCertificate(Integer deviceID, DeviceInfo deviceInfo, IssueCertificateRequest request);
    SubmitReceiptResponse submitReceipt(Integer deviceID, DeviceInfo deviceInfo, SubmitReceiptRequest request);
    PingResponse ping(Integer deviceID, DeviceInfo deviceInfo);
    SubmitFileResponse submitFile(Integer deviceID, DeviceInfo deviceInfo, SubmitFileRequest request);
    SubmittedFileHeaderDtoListResponse getSubmittedFileList(Integer deviceID, DeviceInfo deviceInfo, Integer offset, Integer limit, DeviceSearchCriteria criteria);
}
