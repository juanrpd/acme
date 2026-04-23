package com.seti.acme.technicaltest.commons.dto.wsdl.unmarshaller;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class EnvioPedidoResponse {
    @XmlElement(name = "Codigo")
    private String codigo;

    @XmlElement(name = "Mensaje")
    private String mensaje;

    public String getCodigo() { return codigo; }
    public String getMensaje() { return mensaje; }
}
