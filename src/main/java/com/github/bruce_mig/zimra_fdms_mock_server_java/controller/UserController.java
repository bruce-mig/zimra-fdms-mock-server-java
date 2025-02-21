package com.github.bruce_mig.zimra_fdms_mock_server_java.controller;

import com.github.bruce_mig.zimra_fdms_mock_server_java.annotations.DeviceInfoHeader;
import com.github.bruce_mig.zimra_fdms_mock_server_java.dto.DeviceInfo;
import com.github.bruce_mig.zimra_fdms_mock_server_java.dto.DeviceSearchCriteria;
import com.github.bruce_mig.zimra_fdms_mock_server_java.dto.UserSearchCriteria;
import com.github.bruce_mig.zimra_fdms_mock_server_java.dto.v1.device.DeviceSort;
import com.github.bruce_mig.zimra_fdms_mock_server_java.dto.v1.device.Operator;
import com.github.bruce_mig.zimra_fdms_mock_server_java.dto.v1.device.Order;
import com.github.bruce_mig.zimra_fdms_mock_server_java.dto.v1.user.*;
import com.github.bruce_mig.zimra_fdms_mock_server_java.util.OperationIDCache;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

/**
 * All methods are synchronous.<br>
 * Fiscal Device Gateway uses mutual TLS authentication (<a href="https://en.wikipedia.org/wiki/Mutual_authentication">...</a>)
 * to authenticate fiscal device using fiscal device certificate.<br>
 * Fiscal device certificate is validated against issuing certificate to allow or deny access to API endpoints.<br>
 * After authentication provided fiscal device certificate is checked against issued certificate
 * (see registerDevice and issueCertificate methods for fiscal device certificate issuing)
 * to check if the fiscal device certificate was issued to calling device (by method parameter deviceId) and the fiscal device certificate was not revoked
 */
@RestController
@CrossOrigin
@Validated
@Slf4j
@RequestMapping("/User/v1")
public class UserController {

    public static final String OPERATION_ID = "operationId";
    private final OperationIDCache idCache;

    public UserController(OperationIDCache idCache) {
        this.idCache = idCache;
    }

    /**
     * Endpoint is used to get taxpayer users saved in Fiscal device management sistem list.
     * @param deviceID
     * @param deviceInfo
     * @param offset
     * @param limit
     * @param criteria
     * @param bindingResult
     * @return GetUsersResponse
     */
    @GetMapping("/{deviceID}/GetUsersList")
    public ResponseEntity<?> getUsersList(
            @PathVariable Integer deviceID,
            @DeviceInfoHeader DeviceInfo deviceInfo,
            @RequestParam("Offset") Integer offset,
            @RequestParam("Limit") Integer limit,
            @Valid @ModelAttribute UserSearchCriteria criteria,
            BindingResult bindingResult){

        String operationID = idCache.getOperationID();
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set(OPERATION_ID,operationID);

        // Check for validation errors on the UserSearchCriteria object
        if (bindingResult.hasErrors()) {
            // Handle errors – for example, return a 400 Bad Request with error details
            return ResponseEntity.badRequest().headers(responseHeaders).body(bindingResult.getAllErrors());
        }

        GetUsersResponse response = GetUsersResponse.builder()
                .operatorID(operationID)
                .build();

        return ResponseEntity.ok().headers(responseHeaders).body(response);
    }

    /**
     * Endpoint is responsible for security code sending to taxpayer and branch where device registered emails.
     * @param deviceID
     * @param deviceInfo
     * @param request
     * @return SendSecurityCodeToTaxpayerResponse
     */
    @PostMapping("/{deviceID}/SendSecurityCodeToTaxpayer")
    public ResponseEntity<SendSecurityCodeToTaxpayerResponse> sendSecurityCodeToTaxpayer(@PathVariable Integer deviceID,
                                                        @DeviceInfoHeader DeviceInfo deviceInfo,
                                                        @Valid @RequestBody SendSecurityCodeToTaxpayerRequest request){
        String operationID = idCache.getOperationID();
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set(OPERATION_ID,operationID);

        SendSecurityCodeToTaxpayerResponse response = SendSecurityCodeToTaxpayerResponse.builder()
                .operationID(operationID)
                .build();

        return ResponseEntity.ok().headers(responseHeaders).body(response);
    }

    /**
     * Creates new pos user or updates allready existing not comfirmed tenant user
     * @param deviceID
     * @param deviceInfo
     * @param request
     * @return CreateUserResponse
     */
    @PostMapping("/{deviceID}/CreateUser")
    public ResponseEntity<CreateUserResponse> createUser(
            @PathVariable Integer deviceID,
            @DeviceInfoHeader DeviceInfo deviceInfo,
            @Valid @RequestBody CreateUserRequest request ){

        String operationID = idCache.getOperationID();
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set(OPERATION_ID,operationID);

        CreateUserResponse response = CreateUserResponse.builder()
                .operationID(operationID)
                .build();

        return ResponseEntity.ok().headers(responseHeaders).body(response);
    }

    /**
     * Endpoint is used to check if sent username and password credentials are correct and user can login to POS.
     * @param deviceID
     * @param deviceInfo
     * @param request
     * @return LoginResponse
     */
    @PostMapping("/{deviceID}/Login")
    public ResponseEntity<LoginResponse> login(
            @PathVariable Integer deviceID,
            @DeviceInfoHeader DeviceInfo deviceInfo,
            @Valid @RequestBody LoginRequest request){

        String operationID = idCache.getOperationID();
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set(OPERATION_ID,operationID);

        LoginResponse response = LoginResponse.builder()
                .operationID(operationID)
                .build();

        return ResponseEntity.ok().headers(responseHeaders).body(response);
    }

    /**
     *  Sends security code to confirm user email
     * @param deviceID
     * @param token
     * @param deviceInfo
     * @param request
     * @return
     */
    @PostMapping("/{deviceID}/SendSecurityCodeToUserEmail")
    public ResponseEntity<SendSecurityCodeToUserEmailResponse> sendSecurityCodeToUserEmail(
            @PathVariable Integer deviceID,
            @RequestHeader("Token") String token,
            @DeviceInfoHeader DeviceInfo deviceInfo,
            @Valid @RequestBody SendSecurityCodeToUserEmailRequest request){

        String operationID = idCache.getOperationID();
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set(OPERATION_ID,operationID);

        SendSecurityCodeToUserEmailResponse response = SendSecurityCodeToUserEmailResponse.builder()
                .operationID(operationID)
                .build();

        return ResponseEntity.ok().headers(responseHeaders).body(response);
    }

    /**
     * Sends security code to confirm user phone
     * @param deviceID
     * @param token
     * @param deviceInfo
     * @param request
     * @return
     */
    @PostMapping("/{deviceID}/SendSecurityCodeToUserPhone")
    public ResponseEntity<?> sendSecurityCodeToUserPhone(
            @PathVariable Integer deviceID,
            @RequestHeader("Token") String token,
            @DeviceInfoHeader DeviceInfo deviceInfo,
            @Valid @RequestBody SendSecurityCodeToUserPhoneRequest request){

        String operationID = idCache.getOperationID();
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set(OPERATION_ID,operationID);

        SendSecurityCodeToUserPhoneResponse response = SendSecurityCodeToUserPhoneResponse.builder()
                .operationID(operationID)
                .build();

        return ResponseEntity.ok().headers(responseHeaders).body(response);
    }

    /**
     * Endpoint is user creation confirmation by taxpayer.
     * @param deviceID
     * @param deviceInfo
     * @param request
     * @return
     */
    @PostMapping("/{deviceID}/ConfirmUser")
    public ResponseEntity<ConfirmUserResponse> confirmUser(
            @PathVariable Integer deviceID,
            @DeviceInfoHeader DeviceInfo deviceInfo,
            @Valid @RequestBody ConfirmUserRequest request){

        String operationID = idCache.getOperationID();
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set(OPERATION_ID,operationID);

        ConfirmUserResponse response = ConfirmUserResponse.builder()
                .operationID(operationID)
                .build();

        return ResponseEntity.ok().headers(responseHeaders).body(response);

    }

    /**
     * Endpoint is used to change user password.
     * @param deviceID
     * @param token
     * @param deviceInfo
     * @param request
     * @return
     */
    @PostMapping("/{deviceID}/ChangePassword")
    public ResponseEntity<ChangePasswordResponse> changePassword(
            @PathVariable Integer deviceID,
            @RequestHeader("Token") String token,
            @DeviceInfoHeader DeviceInfo deviceInfo,
            @Valid @RequestBody ChangePasswordRequest request){

        String operationID = idCache.getOperationID();
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set(OPERATION_ID,operationID);

        ChangePasswordResponse response = ChangePasswordResponse.builder()
                .operationID(operationID)
                .build();

        return ResponseEntity.ok().headers(responseHeaders).body(response);

    }

    /**
     * Endpoint is used to change user password.
     * @param deviceID
     * @param deviceInfo
     * @param request
     * @return
     */
    @PostMapping("/{deviceID}/ResetPassword")
    public ResponseEntity<ResetPasswordResponse> resetPassword(
            @PathVariable Integer deviceID,
            @DeviceInfoHeader DeviceInfo deviceInfo,
            @Valid @RequestBody ResetPasswordRequest request){

        String operationID = idCache.getOperationID();
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set(OPERATION_ID,operationID);

        ResetPasswordResponse response = ResetPasswordResponse.builder()
                .operationID(operationID)
                .build();

        return ResponseEntity.ok().headers(responseHeaders).body(response);
    }

    /**
     * Endpoint is used for user contact change confirmation.
     * @param deviceID
     * @param token
     * @param deviceInfo
     * @param request
     * @return
     */
    @PostMapping("/{deviceID}/ConfirmContact")
    public ResponseEntity<ConfirmContactResponse> confirmContact(
            @PathVariable Integer deviceID,
            @RequestHeader("Token") String token,
            @DeviceInfoHeader DeviceInfo deviceInfo,
            @Valid @RequestBody ConfirmContactRequest request){

        String operationID = idCache.getOperationID();
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set(OPERATION_ID,operationID);

        ConfirmContactResponse response = ConfirmContactResponse.builder()
                .operationID(operationID)
                .build();

        return ResponseEntity.ok().headers(responseHeaders).body(response);
    }

    /**
     * Endpoint is used to update user.
     * @param deviceID
     * @param token
     * @param deviceInfo
     * @param request
     * @return
     */
    @PostMapping("/{deviceID}/Update")
    public ResponseEntity<UpdateUserResponse> update(
            @PathVariable Integer deviceID,
            @RequestHeader("Token") String token,
            @DeviceInfoHeader DeviceInfo deviceInfo,
            @Valid @RequestBody UpdateUserRequest request){

        String operationID = idCache.getOperationID();
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set(OPERATION_ID,operationID);

        UpdateUserResponse response = UpdateUserResponse.builder()
                .operationID(operationID)
                .build();

        return ResponseEntity.ok().headers(responseHeaders).body(response);
    }

    /**
     * Endpoint for confirming password reset
     * @param deviceID
     * @param deviceInfo
     * @param request
     * @return
     */
    @PostMapping("/{deviceID}/ConfirmPasswordReset")
    public ResponseEntity<ConfirmPasswordResponse> confirmPasswordReset(
            @PathVariable Integer deviceID,
            @DeviceInfoHeader DeviceInfo deviceInfo,
            @Valid @RequestBody ConfirmPasswordResetRequest request){

        String operationID = idCache.getOperationID();
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set(OPERATION_ID,operationID);

        ConfirmPasswordResponse response = ConfirmPasswordResponse.builder()
                .operationID(operationID)
                .build();

        return ResponseEntity.ok().headers(responseHeaders).body(response);
    }






    /**  The @InitBinder method customizes the binding for UserSearchCriteria. */
    @InitBinder
    public void initBinder(WebDataBinder binder, WebRequest request) {
        Object target = binder.getTarget();
        if (target instanceof UserSearchCriteria criteria) {

            String sort = request.getParameter("Sort");
            if (sort != null) {
                criteria.setSort(GetUsersListEnum.valueOf(sort));
            }

            String order = request.getParameter("Order");
            if (order != null) {
                criteria.setOrder(Order.valueOf(order));
            }

            String operator = request.getParameter("Operator");
            if (operator != null) {
                criteria.setOperator(Operator.valueOf(operator));
            }
        }
    }
}

