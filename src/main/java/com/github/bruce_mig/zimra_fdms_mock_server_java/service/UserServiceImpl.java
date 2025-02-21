package com.github.bruce_mig.zimra_fdms_mock_server_java.service;

import com.github.bruce_mig.zimra_fdms_mock_server_java.dao.UserDAO;
import com.github.bruce_mig.zimra_fdms_mock_server_java.dto.DeviceInfo;
import com.github.bruce_mig.zimra_fdms_mock_server_java.dto.UserSearchCriteria;
import com.github.bruce_mig.zimra_fdms_mock_server_java.dto.v1.user.*;
import com.github.bruce_mig.zimra_fdms_mock_server_java.exceptions.DeviceCertificateExpiredException;
import com.github.bruce_mig.zimra_fdms_mock_server_java.exceptions.DeviceNotFoundException;
import com.github.bruce_mig.zimra_fdms_mock_server_java.exceptions.UnprocessableContentException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    private final UserDAO userDao;

    public UserServiceImpl(UserDAO userDao) {
        this.userDao = userDao;
    }

    @Override
    public GetUsersResponse getUsersList(Integer deviceID, DeviceInfo deviceInfo, Integer offset, Integer limit, UserSearchCriteria criteria) {

        switch (deviceID){
            case 900:
                // Mock expiration of Certificate Device
                throw new DeviceCertificateExpiredException("Device certificate expired");
            case 901:
                // Mock 422
                throw new UnprocessableContentException("Operation failed because of provided data or invalid object state in Fiscal backend");
        }

        return userDao.getUsersResponse();
    }

    @Override
    public SendSecurityCodeToTaxpayerResponse sendSecurityCodeToTaxpayer(Integer deviceID, DeviceInfo deviceInfo, SendSecurityCodeToTaxpayerRequest request) {

        switch (deviceID){
            case 900:
                // Mock expiration of Certificate Device
                throw new DeviceCertificateExpiredException("Device certificate expired");
            case 901:
                // Mock 422
                throw new UnprocessableContentException("Operation failed because of provided data or invalid object state in Fiscal backend");
            case 902:
                throw new DeviceNotFoundException("Not Found");
        }

        return userDao.getSendSecurityCodeToTaxpayerResponse();
    }

    @Override
    public CreateUserResponse createUser(Integer deviceID, DeviceInfo deviceInfo, CreateUserRequest request) {

        switch (deviceID){
            case 900:
                // Mock expiration of Certificate Device
                throw new DeviceCertificateExpiredException("Device certificate expired");
            case 901:
                // Mock 422
                throw new UnprocessableContentException("Operation failed because of provided data or invalid object state in Fiscal backend");
        }

        return userDao.getCreateUserResponse();
    }

    @Override
    public LoginResponse login(Integer deviceID, DeviceInfo deviceInfo, LoginRequest request) {

        switch (deviceID){
            case 900:
                // Mock expiration of Certificate Device
                throw new DeviceCertificateExpiredException("Device certificate expired");
            case 901:
                // Mock 422
                throw new UnprocessableContentException("Operation failed because of provided data or invalid object state in Fiscal backend");
        }

        return userDao.getLoginResponse();
    }

    @Override
    public SendSecurityCodeToUserEmailResponse sendSecurityCodeToUserEmail(Integer deviceID, String token, DeviceInfo deviceInfo, SendSecurityCodeToUserEmailRequest request) {

        switch (deviceID){
            case 900:
                // Mock expiration of Certificate Device
                throw new DeviceCertificateExpiredException("Device certificate expired");
            case 901:
                // Mock 422
                throw new UnprocessableContentException("Operation failed because of provided data or invalid object state in Fiscal backend");
        }

        return new SendSecurityCodeToUserEmailResponse();
    }

    @Override
    public SendSecurityCodeToUserPhoneResponse sendSecurityCodeToUserPhone(Integer deviceID, String token, DeviceInfo deviceInfo, SendSecurityCodeToUserPhoneRequest request) {

        switch (deviceID){
            case 900:
                // Mock expiration of Certificate Device
                throw new DeviceCertificateExpiredException("Device certificate expired");
            case 901:
                // Mock 422
                throw new UnprocessableContentException("Operation failed because of provided data or invalid object state in Fiscal backend");
        }

        return new SendSecurityCodeToUserPhoneResponse();
    }

    @Override
    public ConfirmUserResponse confirmUser(Integer deviceID, DeviceInfo deviceInfo, ConfirmUserRequest request) {

        switch (deviceID){
            case 900:
                // Mock expiration of Certificate Device
                throw new DeviceCertificateExpiredException("Device certificate expired");
            case 901:
                // Mock 422
                throw new UnprocessableContentException("Operation failed because of provided data or invalid object state in Fiscal backend");
        }

        return userDao.getConfirmUserResponse();
    }

    @Override
    public ChangePasswordResponse changePassword(Integer deviceID, String token, DeviceInfo deviceInfo, ChangePasswordRequest request) {

        switch (deviceID){
            case 900:
                // Mock expiration of Certificate Device
                throw new DeviceCertificateExpiredException("Device certificate expired");
            case 901:
                // Mock 422
                throw new UnprocessableContentException("Operation failed because of provided data or invalid object state in Fiscal backend");
        }

        return userDao.getChangePasswordResponse();
    }

    @Override
    public ResetPasswordResponse resetPassword(Integer deviceID, DeviceInfo deviceInfo, ResetPasswordRequest request) {

        switch (deviceID){
            case 900:
                // Mock expiration of Certificate Device
                throw new DeviceCertificateExpiredException("Device certificate expired");
            case 901:
                // Mock 422
                throw new UnprocessableContentException("Operation failed because of provided data or invalid object state in Fiscal backend");
        }

        return new ResetPasswordResponse();
    }

    @Override
    public ConfirmContactResponse confirmContact(Integer deviceID, String token, DeviceInfo deviceInfo, ConfirmContactRequest request) {

        switch (deviceID){
            case 900:
                // Mock expiration of Certificate Device
                throw new DeviceCertificateExpiredException("Device certificate expired");
            case 901:
                // Mock 422
                throw new UnprocessableContentException("Operation failed because of provided data or invalid object state in Fiscal backend");
        }

        return userDao.getConfirmContactResponse();

    }

    @Override
    public UpdateUserResponse update(Integer deviceID, String token, DeviceInfo deviceInfo, UpdateUserRequest request) {

        switch (deviceID){
            case 900:
                // Mock expiration of Certificate Device
                throw new DeviceCertificateExpiredException("Device certificate expired");
            case 901:
                // Mock 422
                throw new UnprocessableContentException("Operation failed because of provided data or invalid object state in Fiscal backend");
        }

        return new UpdateUserResponse();
    }

    @Override
    public ConfirmPasswordResponse confirmPasswordReset(Integer deviceID, DeviceInfo deviceInfo, ConfirmPasswordResetRequest request) {

        switch (deviceID){
            case 900:
                // Mock expiration of Certificate Device
                throw new DeviceCertificateExpiredException("Device certificate expired");
            case 901:
                // Mock 422
                throw new UnprocessableContentException("Operation failed because of provided data or invalid object state in Fiscal backend");
        }

        return userDao.getConfirmPasswordResponse();
    }

}
