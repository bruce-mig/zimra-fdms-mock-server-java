package com.github.bruce_mig.zimra_fdms_mock_server_java.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Unprocessable Content Exception
 * @author Bruce Migeri
 */
@ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)
public class UnprocessableContentException extends RuntimeException {
    public UnprocessableContentException(String message) {
        super(message);
    }
}
