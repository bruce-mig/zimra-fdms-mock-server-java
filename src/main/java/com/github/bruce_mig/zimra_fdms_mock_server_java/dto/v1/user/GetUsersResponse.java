package com.github.bruce_mig.zimra_fdms_mock_server_java.dto.v1.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetUsersResponse {
    private Integer total;
    private List<UsersListDto> rows;

    /**
     * Operation ID assigned by Fiscalisation Backend.
     * example: 0HMPH9AF0QKKE:00000005
     */
    @NotBlank
    @Size(max = 60)
    private String operatorID;
}
