package com.github.bruce_mig.zimra_fdms_mock_server_java.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Device certificate expired Exception
 * @author Bruce Migeri
 */
@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class DeviceNotFoundException extends RuntimeException {
    public DeviceNotFoundException(String message) {
        super(message);
    }
}
