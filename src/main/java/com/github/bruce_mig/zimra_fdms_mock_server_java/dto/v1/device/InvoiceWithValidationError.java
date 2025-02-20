package com.github.bruce_mig.zimra_fdms_mock_server_java.dto.v1.device;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceWithValidationError {

    private Integer receiptCounter;
    private Integer receiptGlobalNo;
    private List<ValidationError> validationErrors;

}
