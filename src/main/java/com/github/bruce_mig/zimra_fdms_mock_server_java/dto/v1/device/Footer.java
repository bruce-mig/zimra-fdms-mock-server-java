package com.github.bruce_mig.zimra_fdms_mock_server_java.dto.v1.device;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Footer {
    private List<FiscalDayCounter> fiscalDayCounters;
    private FiscalDayDeviceSignature fiscalDayDeviceSignature;
    private Integer receiptCounter;
    private LocalDateTime fiscalDayClosed;
}
