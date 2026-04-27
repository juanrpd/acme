package com.seti.acme.technicaltest.registerorder.dto.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter @Setter
public class RegisterOrderException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private final Object detail;
    private final String service;
    private final String message;

}
