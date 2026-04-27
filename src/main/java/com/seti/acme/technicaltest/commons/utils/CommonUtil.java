package com.seti.acme.technicaltest.commons.utils;

import org.springframework.boot.json.JsonParseException;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CommonUtil {

    public static String logObject(Object obj){
        return new GenerateDTO<>().toJson(obj);
    }

    public static String readJsonResponse(String url) {
        ClassLoader loader = ClassLoader.getSystemClassLoader();
        try(Stream<String> stream = Files.lines(Paths.get(Objects.requireNonNull(loader.getResource(url)).toURI()))){
            return stream.parallel().collect(Collectors.joining());
        }catch (Exception e){
            return null;
        }
    }
}
