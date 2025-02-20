package com.github.bruce_mig.zimra_fdms_mock_server_java.dto.v1.device;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DevicesGetSubmittedFileListRequest {
    private Order order;

    @NotNull
    private Integer offset;

    @NotNull
    private Integer limit;

    private Operator operator;
    private Sort sort;

    @Size(max = 60)
    private String operatorID;

    private LocalDateTime fileUploadedFrom;
    private LocalDateTime fileUploadedTill;
}
