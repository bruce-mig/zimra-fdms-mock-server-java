package com.github.bruce_mig.zimra_fdms_mock_server_java.exceptions;

import com.github.bruce_mig.zimra_fdms_mock_server_java.dto.ProblemDetails;
import com.github.bruce_mig.zimra_fdms_mock_server_java.dto.ValidationProblemDetails;
import com.github.bruce_mig.zimra_fdms_mock_server_java.util.OperationIDCache;
import jakarta.validation.ConstraintViolationException;
import lombok.NonNull;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Customized Response Entity Exception Handler
 * @author Bruce Migeri
 */
@ControllerAdvice
public class GlobalResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    public static final String OPERATION_ID = "operationId";
    private final OperationIDCache idCache;

    public GlobalResponseEntityExceptionHandler(OperationIDCache idCache) {
        this.idCache = idCache;
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ProblemDetails> handleAllExceptions(Exception ex, WebRequest request) {

        ProblemDetails errorDetails = ProblemDetails.builder()
                .type("https://httpstatuses.io/500")
                .title("Server encountered temporary issues")
                .status(500)
                .detail(request.getDescription(false))
                .build();

        HttpHeaders headers = createCustomHeaders();
        return new ResponseEntity<>(errorDetails, headers, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(DeviceCertificateExpiredException.class)
    public final ResponseEntity<ProblemDetails> handleDeviceCertificateExpiredException(Exception ex, WebRequest request) {

        ProblemDetails errorDetails = ProblemDetails.builder()
                .type("https://httpstatuses.io/401")
                .title("Device Certificate Expired")
                .status(401)
                .detail(request.getDescription(false))
                .build();

        HttpHeaders headers = createCustomHeaders();
        return new ResponseEntity<>(errorDetails, headers, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(UnprocessableContentException.class)
    public final ResponseEntity<ProblemDetails> handleUnprocessableContentException(Exception ex, WebRequest request) {

        ProblemDetails errorDetails = ProblemDetails.builder()
                .type("https://httpstatuses.io/422")
                .title("Operation failed because of provided data or invalid object state in Fiscal backend.")
                .status(422)
                .detail(request.getDescription(false))
                .build();

        HttpHeaders headers = createCustomHeaders();
        return new ResponseEntity<>(errorDetails, headers, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(DeviceNotFoundException.class)
    public final ResponseEntity<ProblemDetails> handleDeviceNotFoundException(Exception ex, WebRequest request) {

        ProblemDetails errorDetails = ProblemDetails.builder()
                .type("https://httpstatuses.io/404")
                .title("Not Found")
                .status(404)
                .detail("Not existing device with provided device id")
                .build();

        HttpHeaders headers = createCustomHeaders();
        return new ResponseEntity<>(errorDetails, headers, HttpStatus.NOT_FOUND);
    }

    // MethodArgumentNotValidException => for request body validation
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            @NonNull HttpHeaders headers,
            @NonNull HttpStatusCode status, WebRequest request) {

        Map<String, List<String>> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), Collections.singletonList(error.getDefaultMessage()))
        );

        ValidationProblemDetails errorDetails = ValidationProblemDetails.builder()
                .type("https://httpstatuses.io/400")
                .title("Method Argument Not Valid Exception")
                .status(400)
                .detail(request.getDescription(false))
                .instance(String.valueOf(LocalDateTime.now()))
                .errors(errors)
                .build();

        HttpHeaders customHeaders = createCustomHeaders();
        customHeaders.putAll(headers);
        return new ResponseEntity<>(errorDetails, customHeaders, HttpStatus.BAD_REQUEST);
    }

    // ConstraintViolationException => for path variables, query parameters, etc..
    @ExceptionHandler(ConstraintViolationException.class)
    public final  ResponseEntity<ValidationProblemDetails> handleConstraintViolationException(ConstraintViolationException ex, WebRequest request) {
        Map<String, List<String>> errors = new HashMap<>();
        ex.getConstraintViolations().forEach(violation ->
                errors.put(violation.getPropertyPath().toString(), Collections.singletonList(violation.getMessage()))
        );

        ValidationProblemDetails errorDetails = ValidationProblemDetails.builder()
                .type("https://httpstatuses.io/400")
                .title("Constraint Violation Exception")
                .status(400)
                .detail(request.getDescription(false))
                .instance(String.valueOf(LocalDateTime.now()))
                .errors(errors)
                .build();
        HttpHeaders headers = createCustomHeaders();
        return new ResponseEntity<>(errorDetails, headers,HttpStatus.BAD_REQUEST);
    }

    // Helper method to create headers with operationID
    private HttpHeaders createCustomHeaders() {
        String operationID = idCache.getOperationID();
        HttpHeaders headers = new HttpHeaders();
        headers.set(OPERATION_ID,operationID);
        return headers;
    }

}
