package com.github.bruce_mig.zimra_fdms_mock_server_java.dto.v1.device;

public enum FiscalDayProcessingError {
    BadCertificateSignature,
    MissingReceipts,
    ReceiptsWithValidationErrors,
    CountersMismatch,
    InternalError
}
