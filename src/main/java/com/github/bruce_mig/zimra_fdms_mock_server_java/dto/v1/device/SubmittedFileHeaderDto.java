package com.github.bruce_mig.zimra_fdms_mock_server_java.dto.v1.device;

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
public class SubmittedFileHeaderDto {
    private String fileName;
    private LocalDateTime fileUploadDate;
    private Integer deviceId;
    private Integer dayNo;
    private LocalDateTime fiscalDayOpenedAt;
    private Integer fileSequence;
    private LocalDateTime fileProcessingDate;
    private FileProcessingStatus fileProcessingStatus;
    private FileProcessingError fileProcessingError;
    private Boolean hasFooter;

    @Size(max = 100)
    private String operationId;

    @Size(max = 100)
    private String ipAddress;
    private List<InvoiceWithValidationError> invoiceWithValidationErrors;
}
