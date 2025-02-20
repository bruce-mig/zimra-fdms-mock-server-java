package com.github.bruce_mig.zimra_fdms_mock_server_java.dto.v1.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {

    /**
     * Operation ID assigned by Fiscalisation Backend.
     * example: 0HMPH9AF0QKKE:00000005
     */
    @NotBlank
    @Size(max = 60)
    private String operationID;

    @NotNull
    private User user;

    @NotBlank
    @Size(min = 1)
    private String token;
}
