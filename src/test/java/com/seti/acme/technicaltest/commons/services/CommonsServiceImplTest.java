package com.seti.acme.technicaltest.commons.services;

import com.seti.acme.technicaltest.registerorder.dto.exception.RegisterOrderException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.util.UUID;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

public class CommonsServiceImplTest {
    private static final String RESPONSE_NULL   = "RESPONSE_NULL";
    private static final String THROW           = "THROW";
    private static final String TEST            = "test";
    private static final String OK              = "OK";
    private static final String RESPONSE_OK     = "RESPONSE_OK";
    private static final String RESPONSE_NOT_NULL= "RESPONSE_NOT_NULL";

    private HttpClient httpClient;
    private CommonsServiceImpl commonsServiceImpl;

    @BeforeEach
    void setUp(){
        httpClient = Mockito.mock(HttpClient.class);
        commonsServiceImpl = new CommonsServiceImpl();
        ReflectionTestUtils.setField(commonsServiceImpl, "client", httpClient);
    }

    @ParameterizedTest
    @MethodSource("sourceAllTests")
    @DisplayName("allTest")
    void allTest(String action, String type) throws IOException, InterruptedException {
        StringBuilder randomURL = new StringBuilder("https://test");
        randomURL.append(UUID.randomUUID());
        if(action.equals(RESPONSE_NULL)){
            Mockito.when(httpClient.send(Mockito.any(), Mockito.any())).thenReturn(null);
        }else if(action.equals(RESPONSE_NOT_NULL) || action.equals(RESPONSE_OK)){
            HttpResponse<String> mockResponse = Mockito.mock(HttpResponse.class);
            if(action.equals(RESPONSE_OK)){
                Mockito.when(mockResponse.body()).thenReturn(TEST);
            }
            Mockito.when(httpClient.send(Mockito.any(), Mockito.<HttpResponse.BodyHandler<String>>any())).thenReturn(mockResponse);
        }

        if(type.equals(THROW)){
            Assertions.assertThrows(RegisterOrderException.class,  () -> commonsServiceImpl.executePostService(randomURL.toString(), TEST));
        }else if(type.equals(OK)){
            Assertions.assertEquals(TEST, commonsServiceImpl.executePostService(randomURL.toString(), TEST));
        }
    }

    private static Stream<Arguments> sourceAllTests() {
        return Stream.of(
                arguments(RESPONSE_OK, OK),
                arguments(RESPONSE_NULL, THROW),
                arguments(RESPONSE_NOT_NULL, THROW)
        );
    }
}
