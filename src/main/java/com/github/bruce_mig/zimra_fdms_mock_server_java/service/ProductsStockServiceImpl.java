package com.github.bruce_mig.zimra_fdms_mock_server_java.service;

import com.github.bruce_mig.zimra_fdms_mock_server_java.annotations.ValidateFiscalDevice;
import com.github.bruce_mig.zimra_fdms_mock_server_java.dao.ProductsStockDAO;
import com.github.bruce_mig.zimra_fdms_mock_server_java.dto.DeviceInfo;
import com.github.bruce_mig.zimra_fdms_mock_server_java.dto.ProductsSearchCriteria;
import com.github.bruce_mig.zimra_fdms_mock_server_java.dto.v1.products_stock.ProductsStockGetListDtoResponse;
import com.github.bruce_mig.zimra_fdms_mock_server_java.exceptions.DeviceCertificateExpiredException;
import com.github.bruce_mig.zimra_fdms_mock_server_java.exceptions.DeviceNotFoundException;
import com.github.bruce_mig.zimra_fdms_mock_server_java.exceptions.UnprocessableContentException;
import org.springframework.stereotype.Service;

@Service
public class ProductsStockServiceImpl implements ProductsStockService {

    private final ProductsStockDAO productsStockDao;

    public ProductsStockServiceImpl(ProductsStockDAO productsStockDao) {
        this.productsStockDao = productsStockDao;
    }

    @Override
    @ValidateFiscalDevice
    public ProductsStockGetListDtoResponse search(Integer deviceID, DeviceInfo deviceInfo, Integer offset, Integer limit, ProductsSearchCriteria criteria) {

        if(deviceID == 902){
                throw new DeviceNotFoundException("Operation failed because no device were found with provided device id");
        }

        return productsStockDao.getProductsStockGetListDtoResponse();
    }
}
