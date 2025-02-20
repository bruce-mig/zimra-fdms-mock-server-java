package com.github.bruce_mig.zimra_fdms_mock_server_java.dto.v1.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRequest {

    @NotBlank
    @Size(min = 1, max = 100)
    private String userName;

    @NotBlank
    @Size(min = 1, max = 100)
    private String personName;

    @NotBlank
    @Size(min = 1, max = 100)
    private String personSurname;

    @NotBlank
    @Size(min = 1, max = 100)
    private String userRole;
}
