package com.seti.acme.technicaltest.commons.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

@Slf4j
@Component
public class GenerateDTO<T> {

    private ObjectMapper objectMapper;

    public GenerateDTO(){
        this.objectMapper = new ObjectMapper();
    }

    public String toJson(Object obj) {
        try{
            return  this.objectMapper.writeValueAsString(obj);
        }catch (Exception e){
            log.error(Constants.JSON_ERROR+ e.getMessage());
            return null;
        }
    }
}
