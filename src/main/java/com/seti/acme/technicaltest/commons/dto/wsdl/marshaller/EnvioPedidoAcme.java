package com.seti.acme.technicaltest.commons.dto.wsdl.marshaller;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;

@XmlRootElement(name = "EnvioPedidoAcme", namespace = "http://WSDLs/EnvioPedidos/EnvioPedidosAcme")
@XmlAccessorType(XmlAccessType.FIELD)
public class EnvioPedidoAcme {
    @XmlElement(name = "EnvioPedidoRequest")
    public List<EnvioPedidoRequest> pedidos;
}
