package com.github.bruce_mig.zimra_fdms_mock_server_java.aspects;

import com.github.bruce_mig.zimra_fdms_mock_server_java.exceptions.DeviceCertificateExpiredException;
import com.github.bruce_mig.zimra_fdms_mock_server_java.exceptions.DeviceNotFoundException;
import com.github.bruce_mig.zimra_fdms_mock_server_java.exceptions.UnprocessableContentException;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ValidationAspect {

    @Before(value = "com.github.bruce_mig.zimra_fdms_mock_server_java.aspects.CommonPointCuts.validateDevice(deviceID)",
            argNames = "deviceID")
    public void validateDevice(Integer deviceID) {

        switch (deviceID){
            case 900:
                // Mock expiration of Certificate Device
                throw new DeviceCertificateExpiredException("Device certificate expired");
            case 901:
                // Mock 422
                throw new UnprocessableContentException("Operation failed because of provided data or invalid object state in Fiscal backend");
            /*case 902:
                throw new DeviceNotFoundException("Operation failed because no device were found with provided device id");*/
        }
    }
}
