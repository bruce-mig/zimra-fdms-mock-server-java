package com.github.bruce_mig.zimra_fdms_mock_server_java.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Report Not Found Exception
 * @author Bruce Migeri
 */
@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ReportNotFoundException extends RuntimeException {
    public ReportNotFoundException(String message) {
        super(message);
    }
}
