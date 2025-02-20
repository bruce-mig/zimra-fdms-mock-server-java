package com.github.bruce_mig.zimra_fdms_mock_server_java.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * ErrorDetails
 * @author Bruce Migeri
 */
@Getter
@Setter
@AllArgsConstructor
public class ValidationErrorDetails {
    private LocalDateTime timeStamp;
    private String message;
    private String details;
    private Map<String, String> fieldErrors;
}
