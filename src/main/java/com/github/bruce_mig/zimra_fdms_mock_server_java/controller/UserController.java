package com.github.bruce_mig.zimra_fdms_mock_server_java.controller;

import com.github.bruce_mig.zimra_fdms_mock_server_java.annotations.DeviceInfoHeader;
import com.github.bruce_mig.zimra_fdms_mock_server_java.dto.DeviceInfo;
import com.github.bruce_mig.zimra_fdms_mock_server_java.dto.OperationIdHeader;
import com.github.bruce_mig.zimra_fdms_mock_server_java.dto.UserSearchCriteria;
import com.github.bruce_mig.zimra_fdms_mock_server_java.dto.v1.device.Operator;
import com.github.bruce_mig.zimra_fdms_mock_server_java.dto.v1.device.Order;
import com.github.bruce_mig.zimra_fdms_mock_server_java.dto.v1.user.*;
import com.github.bruce_mig.zimra_fdms_mock_server_java.service.UserService;
import com.github.bruce_mig.zimra_fdms_mock_server_java.util.op_id.OperationIDCache;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

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
    private final UserService userService;

    public UserController(OperationIDCache idCache, UserService userService) {
        this.idCache = idCache;
        this.userService = userService;
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

        OperationIdHeader opIdHeader = createOpIdHeader();

        // Check for validation errors on the UserSearchCriteria object
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().headers(opIdHeader.headers()).body(bindingResult.getAllErrors());
        }

        GetUsersResponse response = userService.getUsersList(deviceID, deviceInfo, offset, limit, criteria);
        response.setOperatorID(opIdHeader.operationID());

        return ResponseEntity.ok()
                .headers(opIdHeader.headers())
                .body(response);
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

        SendSecurityCodeToTaxpayerResponse response = userService.sendSecurityCodeToTaxpayer(deviceID, deviceInfo, request);
        OperationIdHeader opIdHeader = createOpIdHeader();
        response.setOperationID(opIdHeader.operationID());

        return ResponseEntity.ok()
                .headers(opIdHeader.headers())
                .body(response);
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
            @Valid @RequestBody CreateUserRequest request){

        CreateUserResponse response = userService.createUser(deviceID, deviceInfo, request);
        OperationIdHeader opIdHeader = createOpIdHeader();
        response.setOperationID(opIdHeader.operationID());

        return ResponseEntity.ok()
                .headers(opIdHeader.headers())
                .body(response);
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

        LoginResponse response = userService.login(deviceID, deviceInfo, request);
        OperationIdHeader opIdHeader = createOpIdHeader();
        response.setOperationID(opIdHeader.operationID());

        return ResponseEntity.ok()
                .headers(opIdHeader.headers())
                .body(response);
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

        SendSecurityCodeToUserEmailResponse response = userService.sendSecurityCodeToUserEmail(deviceID, token, deviceInfo, request);
        OperationIdHeader opIdHeader = createOpIdHeader();
        response.setOperationID(opIdHeader.operationID());

        return ResponseEntity.ok()
                .headers(opIdHeader.headers())
                .body(response);
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

        SendSecurityCodeToUserPhoneResponse response = userService.sendSecurityCodeToUserPhone(deviceID, token, deviceInfo, request);
        OperationIdHeader opIdHeader = createOpIdHeader();
        response.setOperationID(opIdHeader.operationID());

        return ResponseEntity.ok()
                .headers(opIdHeader.headers())
                .body(response);
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

        ConfirmUserResponse response = userService.confirmUser(deviceID, deviceInfo, request);
        OperationIdHeader opIdHeader = createOpIdHeader();
        response.setOperationID(opIdHeader.operationID());

        return ResponseEntity.ok()
                .headers(opIdHeader.headers())
                .body(response);
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

        ChangePasswordResponse response = userService.changePassword(deviceID, token, deviceInfo, request);
        OperationIdHeader opIdHeader = createOpIdHeader();
        response.setOperationID(opIdHeader.operationID());

        return ResponseEntity.ok()
                .headers(opIdHeader.headers())
                .body(response);

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

        ResetPasswordResponse response = userService.resetPassword(deviceID, deviceInfo, request);
        OperationIdHeader opIdHeader = createOpIdHeader();
        response.setOperationID(opIdHeader.operationID());

        return ResponseEntity.ok()
                .headers(opIdHeader.headers())
                .body(response);
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

        ConfirmContactResponse response = userService.confirmContact(deviceID, token, deviceInfo, request);
        OperationIdHeader opIdHeader = createOpIdHeader();
        response.setOperationID(opIdHeader.operationID());

        return ResponseEntity.ok().headers(opIdHeader.headers()).body(response);
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

        UpdateUserResponse response = userService.update(deviceID, token, deviceInfo, request);
        OperationIdHeader opIdHeader = createOpIdHeader();
        response.setOperationID(opIdHeader.operationID());

        return ResponseEntity.ok()
                .headers(opIdHeader.headers())
                .body(response);
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

        ConfirmPasswordResponse response = userService.confirmPasswordReset(deviceID, deviceInfo, request);
        OperationIdHeader opIdHeader = createOpIdHeader();
        response.setOperationID(opIdHeader.operationID());

        return ResponseEntity.ok()
                .headers(opIdHeader.headers())
                .body(response);
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

    private OperationIdHeader createOpIdHeader() {
        String operationID = idCache.getOperationID();
        HttpHeaders headers = new HttpHeaders();
        headers.set(OPERATION_ID,operationID);

        return OperationIdHeader.builder()
                .operationID(operationID)
                .headers(headers)
                .build();

    }
}

