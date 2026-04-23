package com.seti.acme.technicaltest.commons.dto.wsdl.unmarshaller;
import jakarta.xml.bind.annotation.*;

@XmlRootElement(name = "Envelope", namespace = "http://schemas.xmlsoap.org/soap/envelope/")
@XmlAccessorType(XmlAccessType.FIELD)
public class SoapResponseEnvelope {
    @XmlElement(name = "Body", namespace = "http://schemas.xmlsoap.org/soap/envelope/")
    private SoapResponseBody body;

    public SoapResponseBody getBody() { return body; }

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class SoapResponseBody {
        @XmlElement(name = "EnvioPedidoAcmeResponse", namespace = "http://WSDLs/EnvioPedidos/EnvioPedidosAcme")
        private EnvioPedidoAcmeResponse envioPedidoAcmeResponse;
        public EnvioPedidoAcmeResponse getEnvioPedidoAcmeResponse() { return envioPedidoAcmeResponse; }
    }
}
