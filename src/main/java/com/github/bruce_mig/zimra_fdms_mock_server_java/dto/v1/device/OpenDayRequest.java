package com.github.bruce_mig.zimra_fdms_mock_server_java.dto.v1.device;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OpenDayRequest {

    /** Fiscal day number assigned by device.
     * If this field is not sent, Fiscalisation Backend will generate fiscal day number and return to device.
     * Validation rules:
     * @apiNote fiscalDayNo must be equal to 1 for the first fiscal day of fiscal device
     * fiscalDayNo must be greater by one from the last closed fiscal day fiscalDayNo.
     * example */
    private Integer fiscalDayNo;

    /**
     * Date and time when fiscal day was opened on a device.
     * Time is provided in local time without time zone information.
     * example: 2023-03-30T18:38:54
     */
    @NotNull
    private LocalDateTime fiscalDayOpened;
}
