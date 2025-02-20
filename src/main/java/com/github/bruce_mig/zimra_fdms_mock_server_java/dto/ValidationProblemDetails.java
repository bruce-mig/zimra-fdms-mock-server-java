package com.github.bruce_mig.zimra_fdms_mock_server_java.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ValidationProblemDetails {
    private String type;
    private String title;
    private Integer status;
    private String detail;
    private String instance;
    private Map<String, List<String>> errors;
}
