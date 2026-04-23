package com.seti.acme.technicaltest.registerorder.services;

import com.seti.acme.technicaltest.registerorder.models.RegisterOrderRequest;
import com.seti.acme.technicaltest.registerorder.models.RegisterOrderResponse;

public interface RegisterOrderService {

    /**
     * service entrusted call SOAP client
     * @param registerOrderRequest input
     * @return RegisterOrderResponse
     */
    RegisterOrderResponse registerOrder(RegisterOrderRequest registerOrderRequest);
}
