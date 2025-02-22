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

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc(addFilters = false)
public class GetConfigTests {

    public static final String TEST_OPERATION_ID = "TEST_OPERATION_ID";
    public static final String RESPONSE_HEADER = "Operationid";

    @Autowired
    private MockMvc mvc;

    @MockBean
    private OperationIDCache idCache;

    @Test
    @DisplayName("Should return status OK for success case")
    void getConfig_returns200() throws Exception {
        LogCaptor logCaptor = LogCaptor.forClass(DeviceController.class);

        String expected = getConfig_returns200_json();

        given(idCache.getOperationID())
                .willReturn(TEST_OPERATION_ID);

        ResultActions resultActions = mvc.perform(get("/Device/v1/{deviceID}/GetConfig", 100)
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
                    url: "/Device/v1/100/GetConfig"
                    query: { }
                """;


//        assertTrue(logCaptor.getInfoLogs().contains(testLogs));
        JSONAssert.assertEquals(expected, jsonResponse, false);

        DocumentContext ctx = JsonPath.parse(jsonResponse);
        Integer noOfApplicableTaxes = ctx.read("$.applicableTaxes.length()");
        String deviceOperatingMode = ctx.read("$.deviceOperatingMode");
        String deviceSerialNo = ctx.read("$.deviceSerialNo");
        String email = ctx.read("$.deviceBranchContacts.email");

        assertEquals(3, noOfApplicableTaxes);
        assertEquals("Online", deviceOperatingMode);
        assertEquals("SN-1", deviceSerialNo);
        assertEquals("Leland_Gutmann@yahoo.com", email);

        verify(idCache, times(1)).getOperationID();

    }

    @Test
    @DisplayName("Should return status OK even when 'DeviceModelName' and 'DeviceModelVersion' headers are not present")
    void getConfig_Missing_Device_Headers_returns200() throws Exception {
        LogCaptor logCaptor = LogCaptor.forClass(DeviceController.class);

        String expected = getConfig_returns200_json();

        given(idCache.getOperationID())
                .willReturn(TEST_OPERATION_ID);

        ResultActions resultActions = mvc.perform(get("/Device/v1/{deviceID}/GetConfig", 100)
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
                    url: "/Device/v1/100/GetConfig"
                    query: { }
                """;

//        assertTrue(logCaptor.getInfoLogs().contains(testLogs));
        JSONAssert.assertEquals(expected, jsonResponse, false);

        DocumentContext ctx = JsonPath.parse(jsonResponse);
        Integer noOfApplicableTaxes = ctx.read("$.applicableTaxes.length()");
        String deviceOperatingMode = ctx.read("$.deviceOperatingMode");
        String deviceSerialNo = ctx.read("$.deviceSerialNo");
        String email = ctx.read("$.deviceBranchContacts.email");

        assertEquals(3, noOfApplicableTaxes);
        assertEquals("Online", deviceOperatingMode);
        assertEquals("SN-1", deviceSerialNo);
        assertEquals("Leland_Gutmann@yahoo.com", email);

        verify(idCache, times(1)).getOperationID();

    }

    @Test
    @DisplayName("Should return status 401 UNAUTHORIZED when deviceID is 900")
    void getConfig_StringDeviceID900_returns401() throws Exception {
        LogCaptor logCaptor = LogCaptor.forClass(DeviceController.class);

        String expected = getConfig_returns401_json();

        given(idCache.getOperationID())
                .willReturn(TEST_OPERATION_ID);

        ResultActions resultActions = mvc.perform(get("/Device/v1/{deviceID}/GetConfig", 900)
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
                    url: "/Device/v1/900/GetConfig"
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
        assertEquals("uri=/Device/v1/900/GetConfig", detail);
        assertNull(instance);

        verify(idCache, times(1)).getOperationID();

    }

    @Test
    @DisplayName("Should return status 422 UNPROCESSABLE CONTENT when deviceID is 901")
    void getConfig_StringDeviceID901_returns422() throws Exception {
        LogCaptor logCaptor = LogCaptor.forClass(DeviceController.class);

        String expected = getConfig_returns422_json();

        given(idCache.getOperationID())
                .willReturn(TEST_OPERATION_ID);

        ResultActions resultActions = mvc.perform(get("/Device/v1/{deviceID}/GetConfig", 901)
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
                    url: "/Device/v1/901/GetConfig"
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
        assertEquals("uri=/Device/v1/901/GetConfig", detail);
        assertNull(instance);

        verify(idCache, times(1)).getOperationID();

    }

    private CustomComparator ignoreOperationIdComparator(){
        return new CustomComparator(
                JSONCompareMode.LENIENT,
                new Customization("operationID", (expected, actual) -> true)
        );
    }

    String getConfig_returns200_json(){
        return """
                {
                  "operationID": "TEST_OPERATION_ID",
                  "taxPayerName": "Nienow, Hara and Schinner",
                  "taxPayerTIN": "3796605707",
                  "vatNumber": "3899488439",
                  "deviceSerialNo": "SN-1",
                  "deviceBranchName": "Shoes",
                  "deviceBranchAddress": {
                    "province": "Harare",
                    "city": "Harare",
                    "street": "Torey Lakes",
                    "houseNo": "566",
                    "district": null
                  },
                  "deviceBranchContacts": {
                    "phoneNo": "(320) 238-4248",
                    "email": "Leland_Gutmann@yahoo.com"
                  },
                  "deviceOperatingMode": "Online",
                  "taxPayerDayMaxHrs": 24,
                  "applicableTaxes": [
                    {
                      "taxPercent": 15,
                      "taxName": "15%",
                      "validFrom": null,
                      "validTill": null,
                      "taxID": null
                    },
                    {
                      "taxPercent": 0,
                      "taxName": "0%",
                      "validFrom": null,
                      "validTill": null,
                      "taxID": null
                    },
                    {
                      "taxPercent": null,
                      "taxName": "exempt",
                      "validFrom": null,
                      "validTill": null,
                      "taxID": null
                    }
                  ],
                  "certificateValidTill": "2026-03-30T17:15:40",
                  "qrUrl": "www.qrUrl.com",
                  "taxpayerDayEndNotificationHrs": 20
                }
                """;
        }

    String getConfig_returns401_json(){
        return """
                    {
                      "type": "https://httpstatuses.io/401",
                      "title": "Device Certificate Expired",
                      "status": 401,
                      "detail": "uri=/Device/v1/900/GetConfig",
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
                  "detail": "uri=/Device/v1/901/GetConfig",
                  "instance": null
                }
                """;
    }
}
