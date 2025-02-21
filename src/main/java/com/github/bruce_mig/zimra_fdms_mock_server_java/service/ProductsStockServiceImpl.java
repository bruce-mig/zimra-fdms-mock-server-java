package com.github.bruce_mig.zimra_fdms_mock_server_java.service;

import com.github.bruce_mig.zimra_fdms_mock_server_java.dao.ProductsStockDAO;
import com.github.bruce_mig.zimra_fdms_mock_server_java.dto.DeviceInfo;
import com.github.bruce_mig.zimra_fdms_mock_server_java.dto.ProductsSearchCriteria;
import com.github.bruce_mig.zimra_fdms_mock_server_java.dto.v1.products_stock.ProductsStockGetListDtoResponse;
import com.github.bruce_mig.zimra_fdms_mock_server_java.exceptions.DeviceCertificateExpiredException;
import com.github.bruce_mig.zimra_fdms_mock_server_java.exceptions.DeviceNotFoundException;
import com.github.bruce_mig.zimra_fdms_mock_server_java.exceptions.UnprocessableContentException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ProductsStockServiceImpl implements ProductsStockService {

    private final ProductsStockDAO productsStockDao;

    public ProductsStockServiceImpl(ProductsStockDAO productsStockDao) {
        this.productsStockDao = productsStockDao;
    }

    @Override
    public ProductsStockGetListDtoResponse search(Integer deviceID, DeviceInfo deviceInfo, Integer offset, Integer limit, ProductsSearchCriteria criteria) {

        log.info("[DeviceID: '{}' | DeviceModelName: '{}' | DeviceModelVersion: '{}']", deviceID, deviceInfo.deviceModelName(), deviceInfo.deviceModelVersion());

        switch (deviceID){
            case 900:
                // Mock expiration of Certificate Device
                throw new DeviceCertificateExpiredException("Device certificate expired");
            case 901:
                // Mock 422
                throw new UnprocessableContentException("Operation failed because of provided data or invalid object state in Fiscal backend");
            case 902:
                throw new DeviceNotFoundException("Operation failed because no device were found with provided device id");
        }

        return productsStockDao.getProductsStockGetListDtoResponse();
    }
}
