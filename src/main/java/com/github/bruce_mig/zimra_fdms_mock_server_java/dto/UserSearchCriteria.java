package com.github.bruce_mig.zimra_fdms_mock_server_java.dto;

import com.github.bruce_mig.zimra_fdms_mock_server_java.dto.v1.device.Operator;
import com.github.bruce_mig.zimra_fdms_mock_server_java.dto.v1.device.Order;
import com.github.bruce_mig.zimra_fdms_mock_server_java.dto.v1.products_stock.ProductSort;
import com.github.bruce_mig.zimra_fdms_mock_server_java.dto.v1.user.GetUsersListEnum;

public class UserSearchCriteria {
    private GetUsersListEnum sort;
    private Order order;
    private Operator operator;

    public GetUsersListEnum getSort() {
        return sort;
    }

    public void setSort(GetUsersListEnum sort) {
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
