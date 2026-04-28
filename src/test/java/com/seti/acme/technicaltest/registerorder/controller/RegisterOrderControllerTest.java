package com.seti.acme.technicaltest.registerorder.controller;

import com.seti.acme.technicaltest.commons.utils.CommonUtil;
import com.seti.acme.technicaltest.commons.utils.Constants;
import com.seti.acme.technicaltest.registerorder.models.RegisterOrderResponse;
import com.seti.acme.technicaltest.registerorder.services.RegisterOrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import tools.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class RegisterOrderControllerTest {

    private static final String RESPONSE_OK = "payload/registerorder/controller-response-ok.json";
    private static final String REQUEST_OK = "payload/registerorder/controller-request-ok.json";

    private RegisterOrderController registerOrderController;
    private RegisterOrderService    registerOrderService;
    private MockMvc                 mockMvc;
    private ObjectMapper            objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp(){
        registerOrderService    = Mockito.mock(RegisterOrderService.class);
        registerOrderController = new RegisterOrderController(registerOrderService);
        this.mockMvc            = MockMvcBuilders.standaloneSetup(registerOrderController).build();
    }

    @Test
    @DisplayName("test registerOrder controller - ok")
    public void registerOrder() throws Exception {
        RegisterOrderResponse registerOrderResponse = objectMapper.readValue(CommonUtil.readJsonResponse(RESPONSE_OK), RegisterOrderResponse.class);
        Mockito.when(registerOrderService.registerOrder(Mockito.any())).thenReturn(registerOrderResponse);
        mockMvc.perform(MockMvcRequestBuilders.post(Constants.REGISTERORDER_CONTROLLER_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(CommonUtil.readJsonResponse(REQUEST_OK))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
