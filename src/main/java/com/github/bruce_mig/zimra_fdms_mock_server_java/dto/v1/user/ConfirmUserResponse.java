package com.github.bruce_mig.zimra_fdms_mock_server_java.dto.v1.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConfirmUserResponse {
    private String operationID;
    private User user;
    private String token;
}
