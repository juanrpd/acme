package com.seti.acme.technicaltest.registerorder.controller;

import com.seti.acme.technicaltest.commons.utils.CommonUtil;
import com.seti.acme.technicaltest.commons.utils.Constants;
import com.seti.acme.technicaltest.registerorder.models.RegisterOrderRequest;
import com.seti.acme.technicaltest.registerorder.models.RegisterOrderResponse;
import com.seti.acme.technicaltest.registerorder.services.RegisterOrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class RegisterOrderController {

    private final RegisterOrderService registerOrderService;

    @PostMapping( path = Constants.REGISTERORDER_CONTROLLER_PATH)
    public RegisterOrderResponse registerOrder(@RequestBody RegisterOrderRequest registerOrderRequest){
        log.info(Constants.LOG_MARK_2, Constants.COMPLETE_IN, CommonUtil.logObject(registerOrderRequest));
        return registerOrderService.registerOrder(registerOrderRequest);
    }
}
