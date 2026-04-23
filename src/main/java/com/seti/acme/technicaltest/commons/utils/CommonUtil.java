package com.seti.acme.technicaltest.commons.utils;

import org.springframework.boot.json.JsonParseException;

public class CommonUtil {

    public static String logObject(Object obj){
        return new GenerateDTO<>().toJson(obj);
    }
}
