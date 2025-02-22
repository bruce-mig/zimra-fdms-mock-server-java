package com.github.bruce_mig.zimra_fdms_mock_server_java.device_tests;

import com.github.bruce_mig.zimra_fdms_mock_server_java.controller.DeviceController;
import com.github.bruce_mig.zimra_fdms_mock_server_java.util.OperationIDCache;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import nl.altindag.log.LogCaptor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.Customization;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.skyscreamer.jsonassert.comparator.CustomComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc(addFilters = false)
public class GetStatusTests {

    public static final String TEST_OPERATION_ID = "TEST_OPERATION_ID";
    public static final String RESPONSE_HEADER = "Operationid";

    @Autowired
    private MockMvc mvc;

    @MockBean
    private OperationIDCache idCache;

    @Test
    @DisplayName("Should return status OK for success case")
    void getStatus_returns200() throws Exception {
        LogCaptor logCaptor = LogCaptor.forClass(DeviceController.class);

        String expected = getStatus_returns200_json();

        given(idCache.getOperationID())
                .willReturn(TEST_OPERATION_ID);

        ResultActions resultActions = mvc.perform(get("/Device/v1/{deviceID}/GetStatus", 100)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("DeviceModelName", "abc")
                        .header("DeviceModelVersion", "def"))
                .andExpect(status().isOk())
                .andExpect(content().json(expected))
                .andExpect(header().exists(RESPONSE_HEADER))
                .andExpect(header().string(RESPONSE_HEADER, TEST_OPERATION_ID));

        String jsonResponse = resultActions.andReturn()
                .getResponse()
                .getContentAsString();

        String testLogs = """
                Incoming request
                  env: "dev"
                  method: "GET"
                  url: "/Device/v1/100/GetStatus"
                  query: { }
                """;


//        assertTrue(logCaptor.getInfoLogs().contains(testLogs));
        JSONAssert.assertEquals(expected, jsonResponse, false);

        DocumentContext ctx = JsonPath.parse(jsonResponse);
        String fiscalDayStatus = ctx.read("$.fiscalDayStatus");
        String fiscalDayReconciliationMode = ctx.read("$.fiscalDayReconciliationMode");
        String certificateThumbprint = ctx.read("$.fiscalDayServerSignature.certificateThumbprint");
        String hash = ctx.read("$.fiscalDayServerSignature.hash");

        assertEquals("FiscalDayClosed", fiscalDayStatus);
        assertEquals("Auto", fiscalDayReconciliationMode);
        assertEquals("b785a0b4d8a734a55ba595d143b4cf72834cd88d", certificateThumbprint);
        assertEquals("Ly9UbzU5ZkxIdnVvUmUyc2xVcE4yZ3JKdTVhZGFvZE9XNmtXMU9ZdmYvYz0=", hash);

        verify(idCache, times(1)).getOperationID();
    }

    @Test
    @DisplayName("Should return status OK even when 'DeviceModelName' and 'DeviceModelVersion' headers are not present")
    void getConfig_Missing_Device_Headers_returns200() throws Exception {
        LogCaptor logCaptor = LogCaptor.forClass(DeviceController.class);

        String expected = getStatus_returns200_json();

        given(idCache.getOperationID())
                .willReturn(TEST_OPERATION_ID);

        ResultActions resultActions = mvc.perform(get("/Device/v1/{deviceID}/GetStatus", 100)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(expected))
                .andExpect(header().exists(RESPONSE_HEADER))
                .andExpect(header().string(RESPONSE_HEADER, TEST_OPERATION_ID));

        String jsonResponse = resultActions.andReturn()
                .getResponse()
                .getContentAsString();

        String testLogs = """
                Incoming request
                  env: "dev"
                  method: "GET"
                  url: "/Device/v1/100/GetStatus"
                  query: { }
                """;


//        assertTrue(logCaptor.getInfoLogs().contains(testLogs));
        JSONAssert.assertEquals(expected, jsonResponse, false);

        DocumentContext ctx = JsonPath.parse(jsonResponse);
        String fiscalDayStatus = ctx.read("$.fiscalDayStatus");
        String fiscalDayReconciliationMode = ctx.read("$.fiscalDayReconciliationMode");
        String certificateThumbprint = ctx.read("$.fiscalDayServerSignature.certificateThumbprint");
        String hash = ctx.read("$.fiscalDayServerSignature.hash");

        assertEquals("FiscalDayClosed", fiscalDayStatus);
        assertEquals("Auto", fiscalDayReconciliationMode);
        assertEquals("b785a0b4d8a734a55ba595d143b4cf72834cd88d", certificateThumbprint);
        assertEquals("Ly9UbzU5ZkxIdnVvUmUyc2xVcE4yZ3JKdTVhZGFvZE9XNmtXMU9ZdmYvYz0=", hash);

        verify(idCache, times(1)).getOperationID();
    }

    @Test
    @DisplayName("Should return status 401 UNAUTHORIZED when deviceID is 900")
    void getConfig_DeviceID900_returns401() throws Exception {
        LogCaptor logCaptor = LogCaptor.forClass(DeviceController.class);

        String expected = getConfig_returns401_json();

        given(idCache.getOperationID())
                .willReturn(TEST_OPERATION_ID);

        ResultActions resultActions = mvc.perform(get("/Device/v1/{deviceID}/GetStatus", 900)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("DeviceModelName", "abc")
                        .header("DeviceModelVersion", "def"))
                .andExpect(status().isUnauthorized())
                .andExpect(content().json(expected))
                .andExpect(header().exists(RESPONSE_HEADER))
                .andExpect(header().string(RESPONSE_HEADER, TEST_OPERATION_ID));

        String jsonResponse = resultActions.andReturn()
                .getResponse()
                .getContentAsString();

        String testLogs = """
                Incoming request
                    env: "dev"
                    method: "GET"
                    url: "/Device/v1/900/GetStatus"
                    query: { }
                """;


//        assertTrue(logCaptor.getInfoLogs().contains(testLogs));
        JSONAssert.assertEquals(expected, jsonResponse, false);

        DocumentContext ctx = JsonPath.parse(jsonResponse);
        String type = ctx.read("$.type");
        String title = ctx.read("$.title");
        Integer status = ctx.read("$.status");
        String detail = ctx.read("$.detail");
        String instance = ctx.read("$.instance");

        assertEquals("https://httpstatuses.io/401", type);
        assertEquals("Device Certificate Expired", title);
        assertEquals(401, status);
        assertEquals("uri=/Device/v1/900/GetStatus", detail);
        assertNull(instance);

        verify(idCache, times(1)).getOperationID();

    }

    @Test
    @DisplayName("Should return status 422 UNPROCESSABLE CONTENT when deviceID is 901")
    void getConfig_DeviceID901_returns422() throws Exception {
        LogCaptor logCaptor = LogCaptor.forClass(DeviceController.class);

        String expected = getConfig_returns422_json();

        given(idCache.getOperationID())
                .willReturn(TEST_OPERATION_ID);

        ResultActions resultActions = mvc.perform(get("/Device/v1/{deviceID}/GetStatus", 901)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("DeviceModelName", "abc")
                        .header("DeviceModelVersion", "def"))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(content().json(expected))
                .andExpect(header().exists(RESPONSE_HEADER))
                .andExpect(header().string(RESPONSE_HEADER, TEST_OPERATION_ID));

        String jsonResponse = resultActions.andReturn()
                .getResponse()
                .getContentAsString();

        String testLogs = """
                Incoming request
                    env: "dev"
                    method: "GET"
                    url: "/Device/v1/901/GetStatus"
                    query: { }
                """;


//        assertTrue(logCaptor.getInfoLogs().contains(testLogs));
        JSONAssert.assertEquals(expected, jsonResponse, false);

        DocumentContext ctx = JsonPath.parse(jsonResponse);
        String type = ctx.read("$.type");
        String title = ctx.read("$.title");
        Integer status = ctx.read("$.status");
        String detail = ctx.read("$.detail");
        String instance = ctx.read("$.instance");

        assertEquals("https://httpstatuses.io/422", type);
        assertEquals("Operation failed because of provided data or invalid object state in Fiscal backend.", title);
        assertEquals(422, status);
        assertEquals("uri=/Device/v1/901/GetStatus", detail);
        assertNull(instance);

        verify(idCache, times(1)).getOperationID();

    }

    String getStatus_returns200_json(){
        return """
                {
                    "operationID": "TEST_OPERATION_ID",
                    "fiscalDayStatus": "FiscalDayClosed",
                    "fiscalDayReconciliationMode": "Auto",
                    "fiscalDayServerSignature": {
                      "hash": "Ly9UbzU5ZkxIdnVvUmUyc2xVcE4yZ3JKdTVhZGFvZE9XNmtXMU9ZdmYvYz0=",
                      "signature": "WXlYVFNpekJCck1qTWs0VlFMK3NDTnIrMkFDNmFRYkRBbjlKTVYycmszeUo2TURad2llMHdxUVczb2lzTldyTWtlWnN1QXlGU25Ga1UyQStwS205MXNPSFZkamVSQmVialFnQVFRSU1UQ1ZJY1lyeCtCaXpRN0liOWlDZHNWSStKZWwyblRocVFpUXpmUmVmNkVndGdzYUlBTitQVjU1eFNySHZQa0llK0JjPQ==",
                      "certificateThumbprint": "b785a0b4d8a734a55ba595d143b4cf72834cd88d"
                    },
                    "fiscalDayClosed": "2023-03-30T20:15:40",
                    "fiscalDayCounter": null,
                    "lastReceiptGlobalNo": 9931,
                    "lastFiscalDayNo": 101,
                    "fiscalDayClosingErrorCode": null,
                    "fiscalDayDocumentQuantities": null
                  }
                """;
        }

    String getConfig_returns401_json(){
        return """
                    {
                      "type": "https://httpstatuses.io/401",
                      "title": "Device Certificate Expired",
                      "status": 401,
                      "detail": "uri=/Device/v1/900/GetStatus",
                      "instance": null
                    }
                    """;
    }

    String getConfig_returns422_json(){
        return """
                {
                  "type": "https://httpstatuses.io/422",
                  "title": "Operation failed because of provided data or invalid object state in Fiscal backend.",
                  "status": 422,
                  "detail": "uri=/Device/v1/901/GetStatus",
                  "instance": null
                }
                """;
    }
}
