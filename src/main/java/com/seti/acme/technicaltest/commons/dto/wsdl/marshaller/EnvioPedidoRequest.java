package com.seti.acme.technicaltest.commons.dto.wsdl.marshaller;
import jakarta.xml.bind.annotation.XmlElement;

public class EnvioPedidoRequest {
    @XmlElement(name = "pedido")
    public String pedido;

    @XmlElement(name = "Cantidad")
    public Integer cantidad;

    @XmlElement(name = "EAN")
    public String ean;

    @XmlElement(name = "Producto")
    public String producto;

    @XmlElement(name = "Cedula")
    public String cedula;

    @XmlElement(name = "Direccion")
    public String direccion;
}
