package com.seti.acme.technicaltest.registerorder.services;

import com.seti.acme.technicaltest.commons.dto.wsdl.marshaller.EnvioPedidoAcme;
import com.seti.acme.technicaltest.commons.dto.wsdl.marshaller.EnvioPedidoRequest;
import com.seti.acme.technicaltest.commons.dto.wsdl.unmarshaller.EnvioPedidoResponse;
import com.seti.acme.technicaltest.commons.services.CommonsService;
import com.seti.acme.technicaltest.registerorder.models.RegisterOrderRequest;
import com.seti.acme.technicaltest.registerorder.models.RegisterOrderResponse;
import com.seti.acme.technicaltest.registerorder.utils.RegisterOrderUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class RegisterOrderServiceImpl implements RegisterOrderService {

    private final CommonsService commonsService;

    @Value("${seti.acme.mockurl}")
    private String mockUrl;

    @Override
    public RegisterOrderResponse registerOrder(RegisterOrderRequest registerOrderRequest) {
        EnvioPedidoRequest envioPedidoRequest = RegisterOrderUtil.orderDtoToEnvioPedidoRequestMapper(registerOrderRequest.getOrderDTO());
        EnvioPedidoAcme envioPedidoAcme = new EnvioPedidoAcme();
        envioPedidoAcme.pedidos = (List.of(envioPedidoRequest));
        String xml = RegisterOrderUtil.convertToXml(envioPedidoAcme);

        String response = commonsService.executePostService(mockUrl, xml);
        if(Objects.nonNull(response)){
            EnvioPedidoResponse envioPedidoResponse = RegisterOrderUtil.convertXmlToObject(response);
            return RegisterOrderUtil.buildResponse(true, envioPedidoResponse);
        }else{
            return RegisterOrderUtil.buildResponse(false, null);
        }
    }
}
