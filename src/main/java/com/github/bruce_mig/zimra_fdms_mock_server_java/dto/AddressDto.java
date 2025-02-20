package com.github.bruce_mig.zimra_fdms_mock_server_java.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {

    @Size(max = 100)
    private String province;

    @Size(max = 100)
    private String city;

    @Size(max = 100)
    private String street;

    @Size(max = 250)
    private String houseNo;

    @Size(max = 100)
    private String district;
}
