package com.seti.acme.technicaltest.registerorder.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.seti.acme.technicaltest.commons.dto.DataHeaderResponseTypeDTO;
import com.seti.acme.technicaltest.registerorder.dto.RegisterOrderDataDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RegisterOrderResponse {

    @JsonProperty("dataHeader")
    private DataHeaderResponseTypeDTO dataHeader;

    @JsonProperty("data")
    private RegisterOrderDataDTO data;
}
