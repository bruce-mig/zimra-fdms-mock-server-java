package com.github.bruce_mig.zimra_fdms_mock_server_java.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * ErrorDetails
 * @author Bruce Migeri
 */
@Getter
@Setter
@AllArgsConstructor
public class ErrorDetails {
    private LocalDateTime timeStamp;
    private String message;
    private String details;
}
