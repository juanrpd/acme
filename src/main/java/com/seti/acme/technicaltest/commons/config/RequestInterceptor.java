package com.seti.acme.technicaltest.commons.config;

import com.seti.acme.technicaltest.commons.utils.CommonUtil;
import com.seti.acme.technicaltest.commons.utils.Constants;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@Slf4j
public class RequestInterceptor implements HandlerInterceptor {

    @Override
    public  boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        log.info(Constants.LOG_MARK_1, requestURI);
        return true;
    }
}
