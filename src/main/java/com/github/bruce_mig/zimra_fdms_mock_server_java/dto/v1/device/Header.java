package com.github.bruce_mig.zimra_fdms_mock_server_java.dto.v1.device;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Header {
    private Integer deviceID;
    private Integer fiscalDayNo;
    private LocalDateTime fiscalDayOpened;
    private Integer fileSequence;
}
