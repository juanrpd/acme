package com.seti.acme.technicaltest.registerorder.utils;

import com.seti.acme.technicaltest.commons.dto.BusinessErrorTypeDTO;
import com.seti.acme.technicaltest.commons.dto.DataHeaderResponseTypeDTO;
import com.seti.acme.technicaltest.commons.dto.wsdl.marshaller.EnvioPedidoAcme;
import com.seti.acme.technicaltest.commons.dto.wsdl.marshaller.EnvioPedidoRequest;
import com.seti.acme.technicaltest.commons.dto.wsdl.marshaller.SoapEnvelope;
import com.seti.acme.technicaltest.commons.dto.wsdl.unmarshaller.EnvioPedidoResponse;
import com.seti.acme.technicaltest.commons.dto.wsdl.unmarshaller.SoapResponseEnvelope;
import com.seti.acme.technicaltest.commons.utils.Constants;
import com.seti.acme.technicaltest.registerorder.dto.OrderDTO;
import com.seti.acme.technicaltest.registerorder.dto.RegisterOrderDataDTO;
import com.seti.acme.technicaltest.registerorder.dto.SendOrderResponseDTO;
import com.seti.acme.technicaltest.registerorder.dto.exception.RegisterOrderException;
import com.seti.acme.technicaltest.registerorder.models.RegisterOrderResponse;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import lombok.extern.slf4j.Slf4j;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.List;
import java.util.Objects;

@Slf4j
public class RegisterOrderUtil {

    public static EnvioPedidoRequest orderDtoToEnvioPedidoRequestMapper(OrderDTO orderDTO){
        EnvioPedidoRequest envioPedidoRequest = new EnvioPedidoRequest();
        if(Objects.isNull(orderDTO)){
            throw new RegisterOrderException(null, RegisterOrderUtil.class.getCanonicalName(), Constants.EMPTY_REQUEST);
        }
        envioPedidoRequest.pedido = orderDTO.getOrderNumber();
        envioPedidoRequest.cantidad = (Objects.nonNull(orderDTO.getOrderQuantity())?Integer.parseInt(orderDTO.getOrderQuantity()):0);
        envioPedidoRequest.ean = orderDTO.getEanCode();
        envioPedidoRequest.producto = orderDTO.getProductName();
        envioPedidoRequest.cedula = orderDTO.getDocumentNumber();
        envioPedidoRequest.direccion = orderDTO.getAddress();
        return envioPedidoRequest;
    }

    public static SendOrderResponseDTO envioPedidoResponseToSendOrderResponseDTOMapper(EnvioPedidoResponse envioPedidoResponse){
        SendOrderResponseDTO sendOrderResponseDTO = new SendOrderResponseDTO();
        if(Objects.nonNull(envioPedidoResponse)){
            sendOrderResponseDTO.setSendCode(envioPedidoResponse.getCodigo());
            sendOrderResponseDTO.setStatus(envioPedidoResponse.getMensaje());
        }
        return sendOrderResponseDTO;
    }

    public static String convertToXml(EnvioPedidoAcme envioPedidoAcme){
        try{
            SoapEnvelope soapEnvelope = new SoapEnvelope();
            soapEnvelope.body = new SoapEnvelope.SoapBody();
            soapEnvelope.body.envioPedidoAcme = envioPedidoAcme;
            JAXBContext context = JAXBContext.newInstance(SoapEnvelope.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
            StringWriter stringWriter = new StringWriter();
            marshaller.marshal(soapEnvelope, stringWriter);
            return stringWriter.toString();
        }catch (JAXBException e){
            log.error(Constants.LOG_MARK_2, Constants.ERROR_TO_XML, e.getMessage());
            throw new RegisterOrderException(null, RegisterOrderUtil.class.getCanonicalName(), Constants.ERROR_TO_XML);
        }
    }

    public static EnvioPedidoResponse convertXmlToObject(String xmlString){
        try{
            JAXBContext context = JAXBContext.newInstance(SoapResponseEnvelope.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            StringReader reader = new StringReader(xmlString);
            SoapResponseEnvelope envelope = (SoapResponseEnvelope) unmarshaller.unmarshal(reader);
            if (envelope.getBody() != null && envelope.getBody().getEnvioPedidoAcmeResponse() != null) {
                return envelope.getBody().getEnvioPedidoAcmeResponse().getResponse();
            }else{
                throw new RegisterOrderException(null, RegisterOrderUtil.class.getCanonicalName(), Constants.ERROR_TO_OBJ);
            }
        }catch (Exception e){
            log.error(Constants.LOG_MARK_2, Constants.ERROR_TO_OBJ, e.getMessage());
            throw new RegisterOrderException(null, RegisterOrderUtil.class.getCanonicalName(), Constants.ERROR_TO_OBJ);
        }
    }

    public static RegisterOrderResponse buildResponse(Boolean isSuccess, EnvioPedidoResponse envioPedidoResponse, String detailError){
        RegisterOrderResponse registerOrderResponse = new RegisterOrderResponse();
        RegisterOrderDataDTO registerOrderDataDTO = new RegisterOrderDataDTO();
        DataHeaderResponseTypeDTO dataHeaderResponseTypeDTO = new DataHeaderResponseTypeDTO();

        if(isSuccess){
            SendOrderResponseDTO sendOrderResponseDTO = envioPedidoResponseToSendOrderResponseDTOMapper(envioPedidoResponse);
            registerOrderDataDTO.setSendOrderResponseDTO(sendOrderResponseDTO);
            dataHeaderResponseTypeDTO.setResponseCode(200);
        }else{
            log.error(Constants.LOG_MARK_1, detailError);
            registerOrderDataDTO.setSendOrderResponseDTO(null);
            dataHeaderResponseTypeDTO.setResponseCode(400);
            BusinessErrorTypeDTO businessErrorTypeDTO = BusinessErrorTypeDTO.builder().typeError("ERROR NEGOCIO").messageError("HA OCURRIDO UN ERROR, INTENTE MAS TARDE").build();
            dataHeaderResponseTypeDTO.setErrors(List.of(businessErrorTypeDTO));
        }
        registerOrderResponse.setData(registerOrderDataDTO);
        registerOrderResponse.setDataHeader(dataHeaderResponseTypeDTO);
        return registerOrderResponse;
    }
}