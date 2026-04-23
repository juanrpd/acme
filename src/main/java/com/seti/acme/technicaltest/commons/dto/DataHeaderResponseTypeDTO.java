package com.seti.acme.technicaltest.commons.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class DataHeaderResponseTypeDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("codigoRespuesta")
    private Integer responseCode;

    @JsonProperty("errores")
    private List<BusinessErrorTypeDTO> errors;
}
