package com.seti.acme.technicaltest.commons.dto.wsdl.unmarshaller;
import jakarta.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
public class EnvioPedidoAcmeResponse {

    @XmlElement(name = "EnvioPedidoResponse")
    private EnvioPedidoResponse response;

    public EnvioPedidoResponse getResponse() { return response; }
}
