package com.github.bruce_mig.zimra_fdms_mock_server_java.dao;

import com.github.bruce_mig.zimra_fdms_mock_server_java.dto.v1.user.*;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class UserDAO {

    public GetUsersResponse getUsersResponse() {

        UsersListDto rows = UsersListDto.builder()
                .userName("admin")
                .personName("John")
                .personSurname("Smith")
                .userRole("admin")
                .email("admin@gmail.com")
                .phoneNo("123456789")
                .userStatus(UserStatus.NotConfirmed)
                .build();

        return GetUsersResponse.builder()
                .total(1)
                .rows(Collections.singletonList(rows))
                .build();
    }


    public SendSecurityCodeToTaxpayerResponse getSendSecurityCodeToTaxpayerResponse() {

        return SendSecurityCodeToTaxpayerResponse.builder()
                .build();
    }

    public CreateUserResponse getCreateUserResponse() {
        return CreateUserResponse.builder()
                .build();
    }

    public LoginResponse getLoginResponse() {
        User user = User.builder()
                .userName("admin")
                .personName("John")
                .personSurname("Smith")
                .userRole("admin")
                .email("admin@gmail.com")
                .phoneNo("123456789")
                .build();

        return LoginResponse.builder()
                .user(user)
                .token("token")
                .build();
    }

    public ConfirmUserResponse getConfirmUserResponse() {
        User user = User.builder()
                .userName("admin")
                .personName("John")
                .personSurname("Smith")
                .userRole("admin")
                .email("admin@gmail.com")
                .phoneNo("123456789")
                .build();

        return ConfirmUserResponse.builder()
                .user(user)
                .token("token")
                .build();
    }

    public ChangePasswordResponse getChangePasswordResponse() {
        User user = User.builder()
                .userName("admin")
                .personName("John")
                .personSurname("Smith")
                .userRole("admin")
                .email("admin@gmail.com")
                .phoneNo("123456789")
                .build();

        return ChangePasswordResponse.builder()
                .user(user)
                .token("token")
                .build();
    }

    public ConfirmContactResponse getConfirmContactResponse() {
        User user = User.builder()
                .userName("admin")
                .personName("John")
                .personSurname("Smith")
                .userRole("admin")
                .email("admin@gmail.com")
                .phoneNo("123456789")
                .build();

        return ConfirmContactResponse.builder()
                .user(user)
                .build();
    }

    public ConfirmPasswordResponse getConfirmPasswordResponse() {
        User user = User.builder()
                .userName("admin")
                .personName("John")
                .personSurname("Smith")
                .userRole("admin")
                .email("admin@gmail.com")
                .phoneNo("123456789")
                .build();

        return ConfirmPasswordResponse.builder()
                .user(user)
                .token("token")
                .build();
    }
}
