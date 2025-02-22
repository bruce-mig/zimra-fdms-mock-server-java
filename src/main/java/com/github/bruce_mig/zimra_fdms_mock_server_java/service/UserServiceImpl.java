package com.github.bruce_mig.zimra_fdms_mock_server_java.service;

import com.github.bruce_mig.zimra_fdms_mock_server_java.annotations.ValidateFiscalDevice;
import com.github.bruce_mig.zimra_fdms_mock_server_java.dao.UserDAO;
import com.github.bruce_mig.zimra_fdms_mock_server_java.dto.DeviceInfo;
import com.github.bruce_mig.zimra_fdms_mock_server_java.dto.UserSearchCriteria;
import com.github.bruce_mig.zimra_fdms_mock_server_java.dto.v1.user.*;
import com.github.bruce_mig.zimra_fdms_mock_server_java.exceptions.DeviceNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    private final UserDAO userDao;

    public UserServiceImpl(UserDAO userDao) {
        this.userDao = userDao;
    }

    @Override
    @ValidateFiscalDevice
    public GetUsersResponse getUsersList(Integer deviceID, DeviceInfo deviceInfo, Integer offset, Integer limit, UserSearchCriteria criteria) {
        return userDao.getUsersResponse();
    }

    @Override
    @ValidateFiscalDevice
    public SendSecurityCodeToTaxpayerResponse sendSecurityCodeToTaxpayer(Integer deviceID, DeviceInfo deviceInfo, SendSecurityCodeToTaxpayerRequest request) {
        if(deviceID == 902){
            throw new DeviceNotFoundException("Not Found");
        }

        return userDao.getSendSecurityCodeToTaxpayerResponse();
    }

    @Override
    @ValidateFiscalDevice
    public CreateUserResponse createUser(Integer deviceID, DeviceInfo deviceInfo, CreateUserRequest request) {
        return userDao.getCreateUserResponse();
    }

    @Override
    @ValidateFiscalDevice
    public LoginResponse login(Integer deviceID, DeviceInfo deviceInfo, LoginRequest request) {
        return userDao.getLoginResponse();
    }

    @Override
    @ValidateFiscalDevice
    public SendSecurityCodeToUserEmailResponse sendSecurityCodeToUserEmail(Integer deviceID, String token, DeviceInfo deviceInfo, SendSecurityCodeToUserEmailRequest request) {
        return new SendSecurityCodeToUserEmailResponse();
    }

    @Override
    @ValidateFiscalDevice
    public SendSecurityCodeToUserPhoneResponse sendSecurityCodeToUserPhone(Integer deviceID, String token, DeviceInfo deviceInfo, SendSecurityCodeToUserPhoneRequest request) {
        return new SendSecurityCodeToUserPhoneResponse();
    }

    @Override
    @ValidateFiscalDevice
    public ConfirmUserResponse confirmUser(Integer deviceID, DeviceInfo deviceInfo, ConfirmUserRequest request) {
        return userDao.getConfirmUserResponse();
    }

    @Override
    @ValidateFiscalDevice
    public ChangePasswordResponse changePassword(Integer deviceID, String token, DeviceInfo deviceInfo, ChangePasswordRequest request) {
        return userDao.getChangePasswordResponse();
    }

    @Override
    @ValidateFiscalDevice
    public ResetPasswordResponse resetPassword(Integer deviceID, DeviceInfo deviceInfo, ResetPasswordRequest request) {
        return new ResetPasswordResponse();
    }

    @Override
    @ValidateFiscalDevice
    public ConfirmContactResponse confirmContact(Integer deviceID, String token, DeviceInfo deviceInfo, ConfirmContactRequest request) {
        return userDao.getConfirmContactResponse();

    }

    @Override
    @ValidateFiscalDevice
    public UpdateUserResponse update(Integer deviceID, String token, DeviceInfo deviceInfo, UpdateUserRequest request) {
        return new UpdateUserResponse();
    }

    @Override
    @ValidateFiscalDevice
    public ConfirmPasswordResponse confirmPasswordReset(Integer deviceID, DeviceInfo deviceInfo, ConfirmPasswordResetRequest request) {
        return userDao.getConfirmPasswordResponse();
    }

}
