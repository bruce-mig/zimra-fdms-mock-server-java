package com.github.bruce_mig.zimra_fdms_mock_server_java.dto;

import com.github.bruce_mig.zimra_fdms_mock_server_java.dto.v1.device.DeviceSort;
import com.github.bruce_mig.zimra_fdms_mock_server_java.dto.v1.device.Operator;
import com.github.bruce_mig.zimra_fdms_mock_server_java.dto.v1.device.Order;

import java.time.LocalDateTime;

public class DeviceSearchCriteria {
    private String operationID;
    private LocalDateTime fileUploadFrom;
    private LocalDateTime fileUploadTill;
    private DeviceSort sort;
    private Order order;
    private Operator operator;

    public String getOperationID() {
        return operationID;
    }

    public void setOperationID(String operationID) {
        this.operationID = operationID;
    }

    public LocalDateTime getFileUploadFrom() {
        return fileUploadFrom;
    }

    public void setFileUploadFrom(LocalDateTime fileUploadFrom) {
        this.fileUploadFrom = fileUploadFrom;
    }

    public LocalDateTime getFileUploadTill() {
        return fileUploadTill;
    }

    public void setFileUploadTill(LocalDateTime fileUploadTill) {
        this.fileUploadTill = fileUploadTill;
    }

    public DeviceSort getSort() {
        return sort;
    }

    public void setSort(DeviceSort deviceSort) {
        this.sort = deviceSort;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Operator getOperator() {
        return operator;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }
}
