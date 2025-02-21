package com.github.bruce_mig.zimra_fdms_mock_server_java.service;

import com.github.bruce_mig.zimra_fdms_mock_server_java.dto.DeviceInfo;
import com.github.bruce_mig.zimra_fdms_mock_server_java.dto.ProductsSearchCriteria;
import com.github.bruce_mig.zimra_fdms_mock_server_java.dto.v1.products_stock.ProductsStockGetListDtoResponse;

public interface ProductsStockService {
    ProductsStockGetListDtoResponse search(Integer deviceID, DeviceInfo deviceInfo, Integer offset, Integer limit, ProductsSearchCriteria criteria);
}
