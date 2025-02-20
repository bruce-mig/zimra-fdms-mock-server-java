package com.github.bruce_mig.zimra_fdms_mock_server_java.dto.v1.device;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BuyerData {
    @Size(max = 200)
    private String buyerRegistrationName;

    @Size(max = 200)
    private String buyerTradeName;

    @Size(min = 9, max = 9)
    private String vatNumber;

    @Size(min = 10, max = 10)
    private String buyerTIN;

    private BuyerContacts buyerContacts;
    private BuyerAddress buyerAddress;
}
