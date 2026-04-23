package com.seti.acme.technicaltest.commons.services;

import com.seti.acme.technicaltest.commons.utils.CommonUtil;
import com.seti.acme.technicaltest.commons.utils.Constants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

@Service
@Slf4j
@RequiredArgsConstructor
public class CommonsServiceImpl implements CommonsService {

    final HttpClient client = HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(10)).build();

    @Override
    public String executePostService(String url, String xml) {
        try{
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header(Constants.CONTENT_TYPE, Constants.TEXT_XML)
                    .header(Constants.SOAP_ACTION, Constants.SOAP_ACTION_VAL)
                    .POST(HttpRequest.BodyPublishers.ofString(xml)).build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        }catch (Exception e){
            log.error(Constants.CLIENT_ERROR+ e.getMessage());
            return null;
        }
    }
}
