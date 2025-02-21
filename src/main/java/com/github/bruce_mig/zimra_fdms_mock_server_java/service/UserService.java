package com.github.bruce_mig.zimra_fdms_mock_server_java.service;

import com.github.bruce_mig.zimra_fdms_mock_server_java.dto.DeviceInfo;
import com.github.bruce_mig.zimra_fdms_mock_server_java.dto.UserSearchCriteria;
import com.github.bruce_mig.zimra_fdms_mock_server_java.dto.v1.user.*;

public interface UserService {
    GetUsersResponse getUsersList(Integer deviceID,DeviceInfo deviceInfo,Integer offset,Integer limit,UserSearchCriteria criteria);
    SendSecurityCodeToTaxpayerResponse sendSecurityCodeToTaxpayer(Integer deviceID, DeviceInfo deviceInfo, SendSecurityCodeToTaxpayerRequest request);
    CreateUserResponse createUser(Integer deviceID, DeviceInfo deviceInfo, CreateUserRequest request);
    LoginResponse login(Integer deviceID, DeviceInfo deviceInfo, LoginRequest request);
    SendSecurityCodeToUserEmailResponse sendSecurityCodeToUserEmail(Integer deviceID, String token, DeviceInfo deviceInfo, SendSecurityCodeToUserEmailRequest request);
    SendSecurityCodeToUserPhoneResponse sendSecurityCodeToUserPhone(Integer deviceID,  String token, DeviceInfo deviceInfo, SendSecurityCodeToUserPhoneRequest request);
    ConfirmUserResponse confirmUser(Integer deviceID, DeviceInfo deviceInfo, ConfirmUserRequest request);
    ChangePasswordResponse changePassword(Integer deviceID, String token, DeviceInfo deviceInfo, ChangePasswordRequest request);
    ResetPasswordResponse resetPassword(Integer deviceID, DeviceInfo deviceInfo, ResetPasswordRequest request);
    ConfirmContactResponse confirmContact(Integer deviceID, String token, DeviceInfo deviceInfo, ConfirmContactRequest request);
    UpdateUserResponse update(Integer deviceID, String token, DeviceInfo deviceInfo, UpdateUserRequest request);
    ConfirmPasswordResponse confirmPasswordReset(Integer deviceID, DeviceInfo deviceInfo, ConfirmPasswordResetRequest request);
}
