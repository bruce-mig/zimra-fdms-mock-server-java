package com.github.bruce_mig.zimra_fdms_mock_server_java.dao;

import com.github.bruce_mig.zimra_fdms_mock_server_java.dto.v1.device.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Component
public class DeviceDAO {

    public GetConfigResponse getConfigResponse() {

        DeviceBranchAddress deviceBranchAddress = DeviceBranchAddress.builder()
                .province("Harare")
                .street("Torey Lakes")
                .houseNo("566")
                .city("Harare")
                .build();

        DeviceBranchContacts deviceBranchContacts = DeviceBranchContacts.builder()
                .phoneNo("(320) 238-4248")
                .email("Leland_Gutmann@yahoo.com")
                .build();

        List<Tax> applicableTaxes = List.of(
                Tax.builder()
                        .taxPercent(15.0)
                        .taxName("15%")
                        .build(),
                Tax.builder()
                        .taxPercent(0.0)
                        .taxName("0%")
                        .build(),
                Tax.builder()
                        .taxName("exempt")
                        .build()
        );

        return GetConfigResponse.builder()
                .taxPayerName("Nienow, Hara and Schinner")
                .taxPayerTIN("3796605707")
                .vatNumber("3899488439")
                .deviceSerialNo("SN-1")
                .deviceBranchName("Shoes")
                .deviceBranchAddress(deviceBranchAddress)
                .deviceBranchContacts(deviceBranchContacts)
                .deviceOperatingMode(DeviceOperatingMode.Online)
                .taxPayerDayMaxHrs(24)
                .applicableTaxes(applicableTaxes)
                .certificateValidTill(LocalDateTime.parse("2026-03-30T17:15:40"))
                .qrUrl("www.qrUrl.com")
                .taxpayerDayEndNotificationHrs(20)
                .build();
    }

    public GetStatusResponse getStatusResponse() {

        FiscalDayServerSignature fiscalDayServerSignature = FiscalDayServerSignature.builder()
                .certificateThumbprint("b785a0b4d8a734a55ba595d143b4cf72834cd88d")
                .hash("//To59fLHvuoRe2slUpN2grJu5adaodOW6kW1OYvf/c=".getBytes())
                .signature("YyXTSizBBrMjMk4VQL+sCNr+2AC6aQbDAn9JMV2rk3yJ6MDZwie0wqQW3oisNWrMkeZsuAyFSnFkU2A+pKm91sOHVdjeRBebjQgAQQIMTCVIcYrx+BizQ7Ib9iCdsVI+Jel2nThqQiQzfRef6EgtgsaIAN+PV55xSrHvPkIe+Bc=".getBytes())
                .build();

        return GetStatusResponse.builder()
                .fiscalDayStatus(FiscalDayStatus.FiscalDayClosed)
                .fiscalDayReconciliationMode(FiscalDayReconciliationMode.Auto)
                .fiscalDayServerSignature(fiscalDayServerSignature)
                .fiscalDayClosed(LocalDateTime.parse("2023-03-30T20:15:40"))
                .lastFiscalDayNo(101)
                .lastReceiptGlobalNo(9931)
                .build();
    }

    public OpenDayResponse getOpenDayResponse() {
        return OpenDayResponse.builder()
                .fiscalDayNo(101)
                .build();
    }

    public CloseDayResponse getCloseDayResponse(){
        return CloseDayResponse.builder().build();
    }

    public IssueCertificateResponse getIssueCertificateResponse() {
        return IssueCertificateResponse.builder()
                .certificate("-----BEGIN CERTIFICATE-----\nMIIC6TCCAdGgAwIBAgIFAKsSzWowDQYJKoZIhvcNAQELBQAwZDELMAkGA1UEBhMC\nTFQxETAPBgNVBAoMCEdvb2QgTHRkMScwJQYDVQQLDB5Hb29kIEx0ZCBDZXJ0aWZp\nY2F0ZSBBdXRob3JpdHkxGTAXBgNVBAMMEEdvb2QgTHRkIFJvb3QgQ0EwHhcNMTkx\nMDAzMTU1NzA1WhcNMjAxMDEyMTU1NzA1WjBfMQswCQYDVQQGEwJUWjERMA8GA1UE\nCAwIWmFuemliYXIxHzAdBgNVBAoMFlphbnppYmFyIFJldmVudWUgQm9hcmQxHDAa\nBgNVBAMME1pSQi1lVkZELTAwMDAwMDAwNDIwWTATBgcqhkjOPQIBBggqhkjOPQMB\nBwNCAAT7v3DvY7pRg4lz2Z87wSMwSX27KwlpYnSRV6WUiPjpq2XsUAbg2lhUN7q3\nmlNJaUzqoKPmop5qURIpqUydXfapo3IwcDAJBgNVHRMEAjAAMB8GA1UdIwQYMBaA\nFK1RXHm1plvaintqlWaXDs1X3LX+MB0GA1UdDgQWBBRqr96XrCUbuwCQawxO0//n\nTOCoNTAOBgNVHQ8BAf8EBAMCBeAwEwYDVR0lBAwwCgYIKwYBBQUHAwIwDQYJKoZI\nhvcNAQELBQADggEBANr1Wk1cVZB96yobFgK3rQQv9oXW+Jle7Jh36J2o4wSSB+RH\nlfMojDrqKVQCLrFDcF+8JIA3RTRKdduIXgBAr13xQ8JkHd1/o23yN6a2DaYgh0wr\nDrndlR6y1yG0vQuurJ3IgXmC0ldM5+VhalgmoCKFV9JsUD+GhOyJ6NWlc0SqvJCs\n3RZLYwZ4MNViPbRy0Kbp0ufY1zTbh02Gw9aVfFzUwL8GS00iMb4MnSav1xur7wQh\nBoF3PpNvu003P7f1eVJ62qVD2LWWntfn0mL1aRmDe2wpMQKAKhxto+sDb2mfJ6G6\nPFtwMHe7BUfiwTzGYqav21h1w/amPkxNVQ7Li4M=\n-----END CERTIFICATE-----")
                .build();
    }

    public SubmitReceiptResponse getSubmitReceiptResponse() {
        ReceiptServerSignature receiptServerSignature = ReceiptServerSignature.builder()
                .certificateThumbprint("F9B295CA65BA22B94F6D4B27E48D08BF6CD7F7C8")
                .hash("F9B295CA65BA22B94F6D4B27E48D08BF6CD7F7C8".getBytes())
                .signature("gz/JZQVw5Mk7vCTVx02hrZEQS1vAnMIEnwVdl/eouL9SkYbmZFrfQLVtfhPwxM2SCzgrqf9dpuQi1/t9u7T1t5Vvl/vkMW8FLH0u2IReOXLakxFx9TNWu7XH20FqjCJLXOB3NYAiVshAHtYpwPmU9gYCJBTwfhKAjmJaYpIkUvXE+cXKsV4Zxuvm7y25jOGs2RlLExmVw2uT53aRLoLbHdIxaelq8Pgx+YEJQNz9/AniRyjQRdOD5FyQgu00IU9SydrcpkM6xA01fHsNnB53ATb6CdGBAXv88I42n6o8E784CI8wCGWTF6lEoN6sMnLQPqyxY9YQj0ZxcvW5xhC9uA==".getBytes())
                .build();

        return SubmitReceiptResponse.builder()
                .receiptID(600)
                .serverDate(LocalDateTime.parse("2023-05-04T16:45:37"))
                .receiptServerSignature(receiptServerSignature)
                .build();
    }

    public PingResponse getPingResponse() {
        return PingResponse.builder()
                .reportingFrequency(5)
                .build();
    }

    public SubmitFileResponse getSubmitFileResponse() {
        return SubmitFileResponse.builder()
                .build();
    }

    public SubmittedFileHeaderDtoListResponse getSubmittedFileHeaderDtoListResponse(Integer deviceID) {

        List<SubmittedFileHeaderDto> rows = List.of(
                SubmittedFileHeaderDto.builder()
                        .fileName("file")
                        .fileUploadDate(LocalDateTime.parse("2025-02-21T16:19:08.312Z"))
                        .deviceId(deviceID)
                        .dayNo(4)
                        .fiscalDayOpenedAt(LocalDateTime.parse("2025-02-21T16:19:08.312Z"))
                        .fileSequence(3)
                        .fileProcessingDate(LocalDateTime.parse("2025-02-21T16:19:08.312Z"))
                        .fileProcessingStatus(FileProcessingStatus.InProgress)
                        .fileProcessingError(FileProcessingError.IncorrectFileFormat)
                        .hasFooter(true)
                        .ipAddress("197.162.2.1")
                        .invoiceWithValidationErrors(Collections.emptyList())
                        .build()
        );

        return SubmittedFileHeaderDtoListResponse.builder()
                .total(1)
                .rows(rows)
                .build();
    }
}
