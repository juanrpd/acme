package com.seti.acme.technicaltest.registerorder.services;

import com.seti.acme.technicaltest.commons.services.CommonsService;
import com.seti.acme.technicaltest.commons.utils.CommonUtil;
import com.seti.acme.technicaltest.registerorder.models.RegisterOrderRequest;
import com.seti.acme.technicaltest.registerorder.models.RegisterOrderResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.util.ReflectionTestUtils;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

public class RegisterOrderServiceImplTest {
    private static final String REQUEST_OK          = "payload/registerorder/controller-request-ok.json";
    private static final String XML_OK              = "payload/registerorder/xml-response.txt";
    private static final String INVALID_XML         = "payload/registerorder/xml-response-invalid.txt";
    private static final String INVALID_XML_CASE    = "INVALID_XML_CASE";
    private static final String REGISTERORDER_OK    = "REGISTERORDER_OK";
    private static final String NULL_XML         = "NULL_XML";
    private static final String NULL_REQUEST        = "NULL_REQUEST";
    private static final String NULL_ORDERDTO       = "NULL_ORDERDTO";
    private static final String WITHOUTQUANTITY     = "WITHOUTQUANTITY";

    private static final String OK      = "OK";
    private static final String THROW   = "THROW";

    private CommonsService commonsService;
    private RegisterOrderServiceImpl registerOrderServiceImpl;
    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp(){
        commonsService = Mockito.mock(CommonsService.class);
        registerOrderServiceImpl = new RegisterOrderServiceImpl(commonsService);
        ReflectionTestUtils.setField(registerOrderServiceImpl, "mockUrl", "test");
    }

    @ParameterizedTest
    @MethodSource("sourceAllTests")
    @DisplayName("allTest")
    void allTest(String action, String type) throws IOException {
        RegisterOrderRequest registerOrderRequest = new RegisterOrderRequest();
        String xml = null;
        if(action.equals(REGISTERORDER_OK) || action.equals(WITHOUTQUANTITY) || action.equals(INVALID_XML_CASE)){
            registerOrderRequest = objectMapper.readValue(CommonUtil.readJsonResponse(REQUEST_OK), RegisterOrderRequest.class);
            if(action.equals(WITHOUTQUANTITY)){
                registerOrderRequest.getOrderDTO().setOrderQuantity(null);
            }
            if(action.equals(INVALID_XML_CASE)){
                xml = new String(new ClassPathResource(INVALID_XML).getInputStream().readAllBytes(),StandardCharsets.UTF_8);
            }else{
                xml = new String(new ClassPathResource(XML_OK).getInputStream().readAllBytes(),StandardCharsets.UTF_8);
            }
            Mockito.when(commonsService.executePostService(Mockito.anyString(), Mockito.anyString())).thenReturn(xml);
        }else if(action.equals(NULL_XML)){
            registerOrderRequest = objectMapper.readValue(CommonUtil.readJsonResponse(REQUEST_OK), RegisterOrderRequest.class);
            Mockito.when(commonsService.executePostService(Mockito.anyString(), Mockito.anyString())).thenReturn(null);
        }else if(action.equals(NULL_REQUEST)){
            registerOrderRequest = null;
        }else if(action.equals(NULL_ORDERDTO)){
            registerOrderRequest = objectMapper.readValue(CommonUtil.readJsonResponse(REQUEST_OK), RegisterOrderRequest.class);
            registerOrderRequest.setOrderDTO(null);
        }

        RegisterOrderResponse registerOrderResponse = registerOrderServiceImpl.registerOrder(registerOrderRequest);

        if(type.equals(OK)){
            Assertions.assertEquals(200, registerOrderResponse.getDataHeader().getResponseCode());
        }else if(type.equals(THROW)){
            Assertions.assertEquals(400, registerOrderResponse.getDataHeader().getResponseCode());
        }
    }

    private static Stream<Arguments> sourceAllTests() {
        return Stream.of(
                arguments(REGISTERORDER_OK, OK),
                arguments(WITHOUTQUANTITY,  OK),
                arguments(INVALID_XML_CASE, THROW),
                arguments(NULL_XML,         THROW),
                arguments(NULL_REQUEST,     THROW),
                arguments(NULL_ORDERDTO,    THROW)
                );
    }
}
