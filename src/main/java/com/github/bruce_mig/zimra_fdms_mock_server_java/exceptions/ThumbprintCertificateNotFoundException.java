package com.github.bruce_mig.zimra_fdms_mock_server_java.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Thumbprint CertificateNotFoundException
 * @author Bruce Migeri
 */
@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ThumbprintCertificateNotFoundException extends RuntimeException {
    public ThumbprintCertificateNotFoundException(String message) {
        super(message);
    }
}
