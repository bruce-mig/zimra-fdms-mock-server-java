package com.github.bruce_mig.zimra_fdms_mock_server_java.dto.v1.device;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreditDebitNote {

    private Integer receiptID;

    private Integer deviceID;

    private Integer receiptGlobalNo;

    private Integer fiscalDayNo;

}
