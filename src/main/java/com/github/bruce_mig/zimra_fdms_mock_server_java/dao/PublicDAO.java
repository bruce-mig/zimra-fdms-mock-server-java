package com.github.bruce_mig.zimra_fdms_mock_server_java.dao;

import com.github.bruce_mig.zimra_fdms_mock_server_java.dto.v1.device.DeviceBranchAddress;
import com.github.bruce_mig.zimra_fdms_mock_server_java.dto.v1.device.DeviceBranchContacts;
import com.github.bruce_mig.zimra_fdms_mock_server_java.dto.v1.public_dto.GetServerCertificateResponse;
import com.github.bruce_mig.zimra_fdms_mock_server_java.dto.v1.public_dto.RegisterDeviceResponse;
import com.github.bruce_mig.zimra_fdms_mock_server_java.dto.v1.public_dto.VerifyTaxpayerInformationResponse;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class PublicDAO {

    public RegisterDeviceResponse getRegisterDeviceResponse() {

        return RegisterDeviceResponse.builder()
                .certificate("-----BEGIN CERTIFICATE-----\nMIIC6TCCAdGgAwIBAgIFAKsSzWowDQYJKoZIhvcNAQELBQAwZDELMAkGA1UEBhMC\nTFQxETAPBgNVBAoMCEdvb2QgTHRkMScwJQYDVQQLDB5Hb29kIEx0ZCBDZXJ0aWZp\nY2F0ZSBBdXRob3JpdHkxGTAXBgNVBAMMEEdvb2QgTHRkIFJvb3QgQ0EwHhcNMTkx\nMDAzMTU1NzA1WhcNMjAxMDEyMTU1NzA1WjBfMQswCQYDVQQGEwJUWjERMA8GA1UE\nCAwIWmFuemliYXIxHzAdBgNVBAoMFlphbnppYmFyIFJldmVudWUgQm9hcmQxHDAa\nBgNVBAMME1pSQi1lVkZELTAwMDAwMDAwNDIwWTATBgcqhkjOPQIBBggqhkjOPQMB\nBwNCAAT7v3DvY7pRg4lz2Z87wSMwSX27KwlpYnSRV6WUiPjpq2XsUAbg2lhUN7q3\nmlNJaUzqoKPmop5qURIpqUydXfapo3IwcDAJBgNVHRMEAjAAMB8GA1UdIwQYMBaA\nFK1RXHm1plvaintqlWaXDs1X3LX+MB0GA1UdDgQWBBRqr96XrCUbuwCQawxO0//n\nTOCoNTAOBgNVHQ8BAf8EBAMCBeAwEwYDVR0lBAwwCgYIKwYBBQUHAwIwDQYJKoZI\nhvcNAQELBQADggEBANr1Wk1cVZB96yobFgK3rQQv9oXW+Jle7Jh36J2o4wSSB+RH\nlfMojDrqKVQCLrFDcF+8JIA3RTRKdduIXgBAr13xQ8JkHd1/o23yN6a2DaYgh0wr\nDrndlR6y1yG0vQuurJ3IgXmC0ldM5+VhalgmoCKFV9JsUD+GhOyJ6NWlc0SqvJCs\n3RZLYwZ4MNViPbRy0Kbp0ufY1zTbh02Gw9aVfFzUwL8GS00iMb4MnSav1xur7wQh\nBoF3PpNvu003P7f1eVJ62qVD2LWWntfn0mL1aRmDe2wpMQKAKhxto+sDb2mfJ6G6\nPFtwMHe7BUfiwTzGYqav21h1w/amPkxNVQ7Li4M=\n-----END CERTIFICATE-----")
                .build();
    }

    public GetServerCertificateResponse getServerCertificateResponse() {

        List<String> certificates = List.of(
                "-----BEGIN CERTIFICATE-----\nMIIDQTCCAikCFFLwDNnN3ZnAriN4wanJF439cX53MA0GCSqGSIb3DQEBCwUAMF0x\nCzAJBgNVBAYTAkxUMRMwEQYDVQQIDApFTEVLVFJFTkFJMRMwEQYDVQQHDApFTEVL\nVFJFTkFJMREwDwYDVQQKDAhldHJvbmlrYTERMA8GA1UEAwwIemltcmFfQ0EwHhcN\nMjMwMzE2MTA1NDAxWhcNMzMwMzEzMTA1NDAxWjBdMQswCQYDVQQGEwJMVDETMBEG\nA1UECAwKRUxFS1RSRU5BSTETMBEGA1UEBwwKRUxFS1RSRU5BSTERMA8GA1UECgwI\nZXRyb25pa2ExETAPBgNVBAMMCHppbXJhX0NBMIIBIjANBgkqhkiG9w0BAQEFAAOC\nAQ8AMIIBCgKCAQEA30dU24HCzSd+y4/ho41at23gh1g2Yjhk8SnUNo5PLn2uoUvj\nUemQNuJFjg14LvBPoubcIfOZW6cp9TCGy8KqG3WyVrT9z9sEl4fQMlEsCegmCEIE\nng7PvtsdJL9CaC7x11KK4az5UpzqUQ2gyYOryF6M8BT6wH5U61og2SWfv4M5ttQc\nbBDFbReeCYBLvSzFisI1CAVnc3CLqLhBN5jHxfraeZyAvLzBFnbYj7RBcv28iGRz\ny6LXtgE9yDeRdtCk8UqrgyMe//LWmlu+mmLb2IdIeD66GkD637FURa9lAcDJksUP\nUep2TyXq44JjMeNz7CyPbJ0wlV49cUlKc+/ZVQIDAQABMA0GCSqGSIb3DQEBCwUA\nA4IBAQAPVhhMAvjpQRm9OqZz3k97/yygqxeNKdTjxc/zVO8gj0pRBclVhxCnfj+P\nA1wc1nBEHvZ0oh03JviGQ8wxTLWUc0vWlZICmST7heC3DeA+xh90mLZOb2kK3cko\nY7kTAQ8cLV+ddI4UI46WQ6q/bhueOZQjMf1K2IP0fUhXxgFtrPXXrlkiUNX4tisg\ncy986/JjIHk2sY3OyBqYeFwq5J6DO2kMfLgHzwlaVWnTiXJ/etK17fynETImldZU\n9qSHYEyURqKuDyjELRThDLDTGwnsL5HU31+RCmGCZuNpjqkdne8hedNISdyCsMvD\ndDY0A7Vf+2WmfxWzg0wbhf6cIjxS\n-----END CERTIFICATE-----",
                "-----BEGIN CERTIFICATE-----\nMIIDcDCCAligAwIBAgIISVJfo63v2tswDQYJKoZIhvcNAQELBQAwXTELMAkGA1UE\nBhMCTFQxEzARBgNVBAgMCkVMRUtUUkVOQUkxEzARBgNVBAcMCkVMRUtUUkVOQUkx\nETAPBgNVBAoMCGV0cm9uaWthMREwDwYDVQQDDAh6aW1yYV9DQTAeFw0yMzAzMjAx\nOTM5NTVaFw0yNjAzMjAyMTIzMTBaMGwxCzAJBgNVBAYTAlpXMREwDwYDVQQIDAha\naW1iYWJ3ZTEsMCoGA1UECgwjWmltYmFid2UgRmlzY2FsaXNhdGlvbiBPcmdhbml6\nYXRpb24xHDAaBgNVBAMME1pSQi1lVkZELTAwMDAwMDAwNDIwWTATBgcqhkjOPQIB\nBggqhkjOPQMBBwNCAAT7v3DvY7pRg4lz2Z87wSMwSX27KwlpYnSRV6WUiPjpq2Xs\nUAbg2lhUN7q3mlNJaUzqoKPmop5qURIpqUydXfapo4HvMIHsMAkGA1UdEwQCMAAw\nHQYDVR0OBBYEFGqv3pesJRu7AJBrDE7T/+dM4Kg1MIGaBgNVHSMEgZIwgY+AFIKH\n58WkIDv0AUhEr+O0qvs6Dk7VoWGkXzBdMQswCQYDVQQGEwJMVDETMBEGA1UECAwK\nRUxFS1RSRU5BSTETMBEGA1UEBwwKRUxFS1RSRU5BSTERMA8GA1UECgwIZXRyb25p\na2ExETAPBgNVBAMMCHppbXJhX0NBghRS8AzZzd2ZwK4jeMGpyReN/XF+dzAOBgNV\nHQ8BAf8EBAMCBeAwEwYDVR0lBAwwCgYIKwYBBQUHAwIwDQYJKoZIhvcNAQELBQAD\nggEBAMkQykLAfhUGYaMR71kdwgFShstjK12v0sg69ruNtnlIajs3H9vaYoZRvPOV\n5jkdKlzF4Erp8D9URTJQD9aNCHqOZg1wSytuQqxedoThaWSYL3eiUf3Rig+II/fG\n/F+sr0pC6QrriJKRHH8aeAUF2jXD/CyI/GcftBIMTr91egV5Bn3Pjwfh8aEFzq7R\nqF4p0p8UBPwJtFUSqC4JkwLkpfG8bMpNHYic97+PRRLlrqiSPrQF/rlLQDC4IpMc\n9oMHuYHi2CmMcpnXLNZhgeFhHpILKOloU/AGtsExDS4gHCm/LfkUAz3p0KTIxnfx\nj5QjByFH8P3rY05BmSdE4aFUnxM=\n-----END CERTIFICATE-----"
        );

        return GetServerCertificateResponse.builder()
                .certificate(certificates)
                .certificateValidTill(LocalDateTime.parse("2026-03-20T21:23:10"))
                .build();
    }

    public VerifyTaxpayerInformationResponse getVerifyTaxpayerInformationResponse() {
        DeviceBranchAddress deviceBranchAddress = DeviceBranchAddress.builder()
                .province("Harare")
                .city("Harare")
                .street("Sesame")
                .houseNo("222")
                .district("North")
                .build();

        DeviceBranchContacts deviceBranchContacts = DeviceBranchContacts.builder()
                .phoneNo("135555")
                .email("device@gmail.com")
                .build();

        return VerifyTaxpayerInformationResponse.builder()
                .taxPayerName("TaxPayerName")
                .taxPayerTIN("TaxPayerTIN")
                .vatNumber("VATNumber")
                .deviceBranchName("Branch 1")
                .deviceBranchAddress(deviceBranchAddress)
                .deviceBranchContacts(deviceBranchContacts)
                .build();
    }
}
