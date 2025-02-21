package com.github.bruce_mig.zimra_fdms_mock_server_java.dto;

import lombok.Builder;
import org.springframework.http.HttpHeaders;


@Builder
public record OperationIdHeader(
        String operationID,
        HttpHeaders headers
) {
}
