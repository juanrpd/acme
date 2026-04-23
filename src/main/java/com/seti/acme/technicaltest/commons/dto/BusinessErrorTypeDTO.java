package com.seti.acme.technicaltest.commons.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public class BusinessErrorTypeDTO implements Serializable {

    @JsonProperty("tipoError")
    private String typeError;

    @JsonProperty("mensajeError")
    private String messageError;
}
