package com.github.bruce_mig.zimra_fdms_mock_server_java.dto;

import com.github.bruce_mig.zimra_fdms_mock_server_java.dto.v1.device.Operator;
import com.github.bruce_mig.zimra_fdms_mock_server_java.dto.v1.device.Order;
import com.github.bruce_mig.zimra_fdms_mock_server_java.dto.v1.device.Sort;

import java.time.LocalDateTime;

public class SearchCriteria {
    private String operationID;
    private LocalDateTime fileUploadFrom;
    private LocalDateTime fileUploadTill;
    private Sort sort;
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

    public Sort getSort() {
        return sort;
    }

    public void setSort(Sort sort) {
        this.sort = sort;
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
