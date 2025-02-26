package com.github.bruce_mig.zimra_fdms_mock_server_java.device_tests;

import com.github.bruce_mig.zimra_fdms_mock_server_java.controller.DeviceController;
import com.github.bruce_mig.zimra_fdms_mock_server_java.util.op_id.OperationIDCache;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import nl.altindag.log.LogCaptor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc(addFilters = false)
public class CloseDayTests {

    public static final String TEST_OPERATION_ID = "TEST_OPERATION_ID";
    public static final String RESPONSE_HEADER = "Operationid";

    @Autowired
    private MockMvc mvc;

    @MockBean
    private OperationIDCache idCache;

    @Test
    @DisplayName("Should return status OK for success case")
    void closeDay_returns200() throws Exception {
        LogCaptor logCaptor = LogCaptor.forClass(DeviceController.class);

        String expected = closeDay_returns200_json();

        given(idCache.getOperationID())
                .willReturn(TEST_OPERATION_ID);

        String requestBody = """
                {
                   "fiscalDayNo": 101,
                   "fiscalDayCounters": [
                     {
                       "fiscalCounterType": "SaleByTax",
                       "fiscalCounterCurrency": "str",
                       "fiscalCounterTaxPercent": 0,
                       "fiscalCounterTaxID": 0,
                       "fiscalCounterMoneyType": "Cash",
                       "fiscalCounterValue": 0
                     }
                   ],
                   "fiscalDayDeviceSignature": {
                     "hash": "string",
                     "signature": "string"
                   },
                   "receiptCounter": 0
                }
                """;
        ResultActions resultActions = mvc.perform(post("/Device/v1/{deviceID}/CloseDay", 100)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody)
                        .header("DeviceModelName", "abc")
                        .header("DeviceModelVersion", "def"))
                .andExpect(status().isAccepted())
                .andExpect(content().json(expected))
                .andExpect(header().exists(RESPONSE_HEADER))
                .andExpect(header().string(RESPONSE_HEADER, TEST_OPERATION_ID));

        String jsonResponse = resultActions.andReturn()
                .getResponse()
                .getContentAsString();

        String testLogs = """
                Incoming request
                  env: "dev"
                  method: "POST"
                  url: "/Device/v1/100/CloseDay"
                  query: { }
                """;


//        assertTrue(logCaptor.getInfoLogs().contains(testLogs));
        JSONAssert.assertEquals(expected, jsonResponse, false);

        DocumentContext ctx = JsonPath.parse(jsonResponse);
        String operationID = ctx.read("$.operationID");

        assertEquals("TEST_OPERATION_ID", operationID);

        verify(idCache, times(1)).getOperationID();
    }

    @Test
    @DisplayName("Should return status OK even when 'DeviceModelName' and 'DeviceModelVersion' headers are not present")
    void getConfig_Missing_Device_Headers_returns200() throws Exception {
        LogCaptor logCaptor = LogCaptor.forClass(DeviceController.class);

        String expected = closeDay_returns200_json();

        given(idCache.getOperationID())
                .willReturn(TEST_OPERATION_ID);

        String requestBody = """
                {
                   "fiscalDayNo": 101,
                   "fiscalDayCounters": [
                     {
                       "fiscalCounterType": "SaleByTax",
                       "fiscalCounterCurrency": "str",
                       "fiscalCounterTaxPercent": 0,
                       "fiscalCounterTaxID": 0,
                       "fiscalCounterMoneyType": "Cash",
                       "fiscalCounterValue": 0
                     }
                   ],
                   "fiscalDayDeviceSignature": {
                     "hash": "string",
                     "signature": "string"
                   },
                   "receiptCounter": 0
                }
                """;
        ResultActions resultActions = mvc.perform(post("/Device/v1/{deviceID}/CloseDay", 100)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isAccepted())
                .andExpect(content().json(expected))
                .andExpect(header().exists(RESPONSE_HEADER))
                .andExpect(header().string(RESPONSE_HEADER, TEST_OPERATION_ID));

        String jsonResponse = resultActions.andReturn()
                .getResponse()
                .getContentAsString();

        String testLogs = """
                Incoming request
                  env: "dev"
                  method: "POST"
                  url: "/Device/v1/100/CloseDay"
                  query: { }
                """;


//        assertTrue(logCaptor.getInfoLogs().contains(testLogs));
        JSONAssert.assertEquals(expected, jsonResponse, false);

        DocumentContext ctx = JsonPath.parse(jsonResponse);
        String operationID = ctx.read("$.operationID");

        assertEquals("TEST_OPERATION_ID", operationID);

        verify(idCache, times(1)).getOperationID();
    }


    @Test
    @DisplayName("Should return status 401 UNAUTHORIZED when deviceID is 900")
    void closeDay_DeviceID900_returns401() throws Exception {
        LogCaptor logCaptor = LogCaptor.forClass(DeviceController.class);

        String expected = closeDay_returns401_json();
        String requestBody = """
                {
                   "fiscalDayNo": 101,
                   "fiscalDayCounters": [
                     {
                       "fiscalCounterType": "SaleByTax",
                       "fiscalCounterCurrency": "str",
                       "fiscalCounterTaxPercent": 0,
                       "fiscalCounterTaxID": 0,
                       "fiscalCounterMoneyType": "Cash",
                       "fiscalCounterValue": 0
                     }
                   ],
                   "fiscalDayDeviceSignature": {
                     "hash": "string",
                     "signature": "string"
                   },
                   "receiptCounter": 0
                }
                """;

        given(idCache.getOperationID())
                .willReturn(TEST_OPERATION_ID);

        ResultActions resultActions = mvc.perform(post("/Device/v1/{deviceID}/CloseDay", 900)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody)
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
                    method: "POST"
                    url: "/Device/v1/900/CloseDay"
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
        assertEquals("uri=/Device/v1/900/CloseDay", detail);
        assertNull(instance);

        verify(idCache, times(1)).getOperationID();

    }

    @Test
    @DisplayName("Should return status 422 UNPROCESSABLE CONTENT when deviceID is 901")
    void openDay_DeviceID901_returns422() throws Exception {
        LogCaptor logCaptor = LogCaptor.forClass(DeviceController.class);

        String expected = closeDay_returns422_json();
        String requestBody = """
                {
                   "fiscalDayNo": 101,
                   "fiscalDayCounters": [
                     {
                       "fiscalCounterType": "SaleByTax",
                       "fiscalCounterCurrency": "str",
                       "fiscalCounterTaxPercent": 0,
                       "fiscalCounterTaxID": 0,
                       "fiscalCounterMoneyType": "Cash",
                       "fiscalCounterValue": 0
                     }
                   ],
                   "fiscalDayDeviceSignature": {
                     "hash": "string",
                     "signature": "string"
                   },
                   "receiptCounter": 0
                }
                """;

        given(idCache.getOperationID())
                .willReturn(TEST_OPERATION_ID);

        ResultActions resultActions = mvc.perform(post("/Device/v1/{deviceID}/CloseDay", 901)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody)
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
                    method: "POST"
                    url: "/Device/v1/901/CloseDay"
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
        assertEquals("uri=/Device/v1/901/CloseDay", detail);
        assertNull(instance);

        verify(idCache, times(1)).getOperationID();

    }

    String closeDay_returns200_json(){
        return """
                {
                  "operationID": "TEST_OPERATION_ID"
                }
                """;
        }

    String closeDay_returns401_json(){
        return """
                    {
                      "type": "https://httpstatuses.io/401",
                      "title": "Device Certificate Expired",
                      "status": 401,
                      "detail": "uri=/Device/v1/900/CloseDay",
                      "instance": null
                    }
                    """;
    }

    String closeDay_returns422_json(){
        return """
                {
                  "type": "https://httpstatuses.io/422",
                  "title": "Operation failed because of provided data or invalid object state in Fiscal backend.",
                  "status": 422,
                  "detail": "uri=/Device/v1/901/CloseDay",
                  "instance": null
                }
                """;
    }
}
