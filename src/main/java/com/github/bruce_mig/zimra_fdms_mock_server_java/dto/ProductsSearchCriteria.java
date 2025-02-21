package com.github.bruce_mig.zimra_fdms_mock_server_java.dto;

import com.github.bruce_mig.zimra_fdms_mock_server_java.dto.v1.device.Operator;
import com.github.bruce_mig.zimra_fdms_mock_server_java.dto.v1.device.Order;
import com.github.bruce_mig.zimra_fdms_mock_server_java.dto.v1.products_stock.ProductSort;

public class ProductsSearchCriteria {
    private String hsCode;
    private String goodName;
    private ProductSort sort;
    private Order order;
    private Operator operator;

    public String getHsCode() {
        return hsCode;
    }

    public void setHsCode(String hsCode) {
        this.hsCode = hsCode;
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public ProductSort getSort() {
        return sort;
    }

    public void setSort(ProductSort sort) {
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
