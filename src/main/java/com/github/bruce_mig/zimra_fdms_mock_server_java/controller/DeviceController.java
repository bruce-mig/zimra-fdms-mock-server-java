package com.github.bruce_mig.zimra_fdms_mock_server_java.controller;

import com.github.bruce_mig.zimra_fdms_mock_server_java.annotations.DeviceInfoHeader;
import com.github.bruce_mig.zimra_fdms_mock_server_java.dto.DeviceInfo;
import com.github.bruce_mig.zimra_fdms_mock_server_java.dto.DeviceSearchCriteria;
import com.github.bruce_mig.zimra_fdms_mock_server_java.dto.OperationIdHeader;
import com.github.bruce_mig.zimra_fdms_mock_server_java.dto.v1.device.*;
import com.github.bruce_mig.zimra_fdms_mock_server_java.service.DeviceService;
import com.github.bruce_mig.zimra_fdms_mock_server_java.util.op_id.OperationIDCache;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

/**
 * All methods except closeDay are synchronous.
 * closeDay method returns response about accepted request synchronously, however processing of information is done asynchronously.
 * Fiscal Device Gateway uses mutual TLS authentication (<a href="https://en.wikipedia.org/wiki/Mutual_authentication">...</a>) to authenticate fiscal device using fiscal device certificate.
 * Fiscal device certificate is validated against issuing certificate to allow or deny access to API endpoints.
 * After authentication provided fiscal device certificate is checked against issued certificate (see registerDevice and issueCertificate methods for fiscal device certificate issuing) to check if the fiscal device certificate was issued to calling device (by method parameter deviceId) and the fiscal device certificate was not revoked
 */
@RestController
@CrossOrigin
@Validated
@Slf4j
@RequestMapping("/Device/v1")
public class DeviceController {

    public static final String OPERATION_ID = "operationId";
    private final OperationIDCache idCache;
    private final DeviceService deviceService;

    public DeviceController(OperationIDCache idCache, DeviceService deviceService) {
        this.idCache = idCache;
        this.deviceService = deviceService;
    }

    /**
     * Endpoint is used to retrieve taxpayers and device information and configuration.
     * @param deviceID - ID of fiscal device
     * @param deviceInfo  record with fields:
     *                   DeviceModelName - Device model name as registered in Tax Authority
     *                   DeviceModelVersion - Device model version number as registered in Tax Authority
     * @return GetConfigResponse
     *
     */
    @GetMapping("/{deviceID}/GetConfig")
    public ResponseEntity<GetConfigResponse> getConfig(@PathVariable Integer deviceID,
                                                       @DeviceInfoHeader DeviceInfo deviceInfo) {

        GetConfigResponse configResponse = deviceService.getConfig(deviceID, deviceInfo);
        OperationIdHeader opIdHeader = createOpIdHeader();
        configResponse.setOperationID(opIdHeader.operationID());

        return ResponseEntity.ok().headers(opIdHeader.headers()).body(configResponse);
    }

    /**
     * Endpoint is used to get fiscal day status.
     * @param deviceID
     * @param deviceInfo
     * @return GetStatusResponse
     */
    @GetMapping("/{deviceID}/GetStatus")
    public ResponseEntity<GetStatusResponse> getStatus(@PathVariable Integer deviceID,
                                                       @DeviceInfoHeader DeviceInfo deviceInfo) {

        GetStatusResponse statusResponse = deviceService.getStatus(deviceID, deviceInfo);
        OperationIdHeader opIdHeader = createOpIdHeader();
        statusResponse.setOperationID(opIdHeader.operationID());

        return ResponseEntity.ok().headers(opIdHeader.headers()).body(statusResponse);
    }

    /**
     * Endpoint is used to open a new fiscal day.
     * Opening of new fiscal day is possible only when previous fiscal day is successfully closed (fiscal day status is “FiscalDayClosed”).
     * @param deviceID
     * @param deviceInfo
     * @param request
     * @return
     */
    @PostMapping("/{deviceID}/OpenDay")
    public ResponseEntity<OpenDayResponse> openDay(@PathVariable Integer deviceID,
                                                       @DeviceInfoHeader DeviceInfo deviceInfo,
                                                       @Valid @RequestBody OpenDayRequest request) {

        OpenDayResponse openDayResponse = deviceService.openDay(deviceID, deviceInfo, request);
        OperationIdHeader opIdHeader = createOpIdHeader();
        openDayResponse.setOperationID(opIdHeader.operationID());

        return ResponseEntity.ok().headers(opIdHeader.headers()).body(openDayResponse);

    }

    /**
     * Endpoint is used to initiate fiscal day closure procedure.
     * This method is allowed when fiscal days status is “FiscalDayOpened” or “FiscalDayCloseFailed”.
     * In case fiscal day contains at least one “Grey” or “Red” receipt (as specified Validation errors),
     * Fiscalisation Backend will respond to closeDay request with error (fiscal day will remain opened).
     * Otherwise if fiscal day does not have “Grey” and “Red” receipts, validation of submitted closeDay request will be executed.
     * In case of fiscal day validation fails (as specified below in “Validation rules”),
     * fiscal day remains opened and its status is changed to “FiscalDayCloseFailed”.
     * @param deviceID
     * @param deviceInfo
     * @param request
     * @return
     */
    // todo: Asynchronous Method
    @PostMapping("/{deviceID}/CloseDay")
    public ResponseEntity<CloseDayResponse> closeDay(@PathVariable Integer deviceID,
                                                   @DeviceInfoHeader DeviceInfo deviceInfo,
                                                   @Valid @RequestBody CloseDayRequest request) {

        CloseDayResponse closeDayResponse = deviceService.closeDay(deviceID, deviceInfo, request);
        OperationIdHeader opIdHeader = createOpIdHeader();
        closeDayResponse.setOperationID(opIdHeader.operationID());

        return ResponseEntity.accepted().headers(opIdHeader.headers()).body(closeDayResponse);
    }

    /**
     * Endpoint is used to renew certificate before the expiration of the current certificate.
     * It is recommended to renew certificate a month before its expiration.
     * Certificate reissue can be done at any time.
     * It does not depend on fiscal day status, however it is recommended to be done before opening a new fiscal day.
     * @param deviceID
     * @param deviceInfo
     * @param request
     * @return
     */
    @PostMapping("/{deviceID}/IssueCertificate")
    public ResponseEntity<IssueCertificateResponse> issueCertificate(@PathVariable Integer deviceID,
                                                     @DeviceInfoHeader DeviceInfo deviceInfo,
                                                     @Valid @RequestBody IssueCertificateRequest request) {

        IssueCertificateResponse issueCertificateResponse = deviceService.issueCertificate(deviceID, deviceInfo, request);
        OperationIdHeader opIdHeader = createOpIdHeader();
        issueCertificateResponse.setOperationID(opIdHeader.operationID());

        return ResponseEntity.ok().headers(opIdHeader.headers()).body(issueCertificateResponse);
    }

    /**
     * Endpoint is used to submit a receipt to Fiscalisation Backend in online mode and get a Fiscalisation Backend signature for it (signature is not a QR code, it is an acknowledgement of Fiscalisation Backend about received receipt).
     * Receipt can be submitted only when fiscal day status is “FiscalDayOpened” or “FiscalDayCloseFailed” and fiscal day is opened not earlier than receiptDate – taxpayerDayMaxHrs. If fiscal day is opened earlier that allowed time, receipt will still be registered, but marked in “Yellow” with validation error RCPT014 (as specified in Validation errors).
     * In case device tried to close a fiscal day and attempt was unsuccessful, device still have a possibility to submit a new receipt. In case the same receipt (with the same deviceID, receiptGlobalNo and receiptHash) is submitted more than once, Fiscal Device Gateway API will return successful result to fiscal device with the same original receipt receiptID, receiptServerSignature, however different operationID.
     * Each submitted receipt is validated. Receipt will not be accepted, error will be returned to fiscal device (as specified in Error codes), in these cases:
     * • fiscal device status is other than “Active”; <br>
     * • fiscal day status is other than “FiscalDayOpened” or “FiscalDayCloseFailed”;<br>
     * • receipt message structure is not valid <br>
     * In case the above-mentioned validations have passed, but receipt has other validation issues specified below (described in “Validation rules”), receipt will be accepted and signed, but will be marked as invalid with validation color code assigned (as specified in 7.3. Validation errors).
     * Each submitted receipt, must increase fiscal day counters as specified in Fiscal counters.
     * @param deviceID
     * @param deviceInfo
     * @param request
     * @return
     */
    @PostMapping("/{deviceID}/SubmitReceipt")
    public ResponseEntity<SubmitReceiptResponse> submitReceipt(@PathVariable Integer deviceID,
                                                                     @DeviceInfoHeader DeviceInfo deviceInfo,
                                                                     @Valid @RequestBody SubmitReceiptRequest request) {

        SubmitReceiptResponse submitReceiptResponse = deviceService.submitReceipt(deviceID, deviceInfo, request);
        OperationIdHeader opIdHeader = createOpIdHeader();
        submitReceiptResponse.setOperationID(opIdHeader.operationID());

        return ResponseEntity.ok().headers(opIdHeader.headers()).body(submitReceiptResponse);

    }

    /**
     * Endpoint is used to report device is online to Fiscalisation Backend.
     * When device is turned on, it must regularly report to Fiscalisation Backend that it is online.
     * Reporting periodicity is specified in reportingFrequency parameter received in response from Fiscalisation Backend.
     * @param deviceID
     * @param deviceInfo
     * @return
     */
    @PostMapping("/{deviceID}/Ping")
    public ResponseEntity<PingResponse> ping(@PathVariable Integer deviceID, @DeviceInfoHeader DeviceInfo deviceInfo) {

        PingResponse pingResponse = deviceService.ping(deviceID, deviceInfo);
        OperationIdHeader opIdHeader = createOpIdHeader();
        pingResponse.setOperationID(opIdHeader.operationID());

        return ResponseEntity.ok().headers(opIdHeader.headers()).body(pingResponse);
    }

    /**
     * Offline Fiscal device receipts
     * @param deviceID
     * @param deviceInfo
     * @param request
     * @return SubmitFileResponse
     */
    @PostMapping("/{deviceID}/SubmitFile")
    public ResponseEntity<SubmitFileResponse> submitFile(@PathVariable Integer deviceID,
                                                               @DeviceInfoHeader DeviceInfo deviceInfo,
                                                               @Valid @RequestBody SubmitFileRequest request) {

        SubmitFileResponse submitFileResponse = deviceService.submitFile(deviceID, deviceInfo, request);
        OperationIdHeader opIdHeader = createOpIdHeader();
        submitFileResponse.setOperationID(opIdHeader.operationID());

        return ResponseEntity.ok().headers(opIdHeader.headers()).body(submitFileResponse);
    }

    /**
     *  Get submitted files list with applied filters
     * @param deviceID
     * @param deviceInfo
     * @param offset
     * @param limit
     * @param criteria
     * @param bindingResult
     * @return
     */
    @GetMapping("/{deviceID}/SubmittedFileList")
    public ResponseEntity<?> getSubmittedFileList(@PathVariable Integer deviceID,
          @DeviceInfoHeader DeviceInfo deviceInfo,
          @RequestParam("Offset") Integer offset,
          @RequestParam("Limit") Integer limit,
          @Valid @ModelAttribute DeviceSearchCriteria criteria,
          BindingResult bindingResult) {

        SubmittedFileHeaderDtoListResponse listResponse = deviceService.getSubmittedFileList(deviceID, deviceInfo, offset, limit, criteria);
        OperationIdHeader opIdHeader = createOpIdHeader();

        // Check for validation errors on the DeviceSearchCriteria object
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().headers(opIdHeader.headers()).body(bindingResult.getAllErrors());
        }

        for (SubmittedFileHeaderDto row : listResponse.getRows()) {
            row.setOperationId(opIdHeader.operationID());
        }

        return ResponseEntity.ok().headers(opIdHeader.headers()).body(listResponse);
    }

    /**  The @InitBinder method customizes the binding for DeviceSearchCriteria. */
    @InitBinder
    public void initBinder(WebDataBinder binder, WebRequest request) {
        Object target = binder.getTarget();
        if (target instanceof DeviceSearchCriteria) {
            DeviceSearchCriteria criteria = (DeviceSearchCriteria) target;

            String opId = request.getParameter("OperationID");
            if (opId != null) {
                criteria.setOperationID(opId);
            }
            String fileUploadedFrom = request.getParameter("FileUploadedFrom");
            if (fileUploadedFrom != null) {
                criteria.setFileUploadFrom(LocalDateTime.parse(fileUploadedFrom));
            }

            String fileUploadedTill = request.getParameter("FileUploadedTill");
            if (fileUploadedTill != null) {
                criteria.setFileUploadTill(LocalDateTime.parse(fileUploadedTill));
            }

            String sort = request.getParameter("DeviceSort");
            if (sort != null) {
                criteria.setSort(DeviceSort.valueOf(sort));
            }

            String order = request.getParameter("Order");
            if (order != null) {
                criteria.setOrder(Order.valueOf(order));
            }

            String operator = request.getParameter("Operator");
            if (operator != null) {
                criteria.setOperator(Operator.valueOf(operator));
            }
        }
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
