package com.jack.handler;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ControllerExceptionHandler {
    private  final Logger _logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 異常處理
     * @param request
     * @param ex
     * @return
     */
    @ExceptionHandler({Exception.class})
    public ModelAndView handleException(HttpServletRequest request, Exception ex) throws Exception {
        _logger.error("Request URL: {}, Exception: {}", request.getRequestURL(), ex.getMessage());

        if(AnnotationUtils.findAnnotation(ex.getClass(), ResponseStatus.class) != null){
            throw ex;
        }
        ModelAndView mav = new ModelAndView();
        mav.addObject("url", request.getRequestURL());
        mav.addObject("exMsg", ex.getMessage());
        mav.addObject("exSt", ex.getStackTrace());
        mav.setViewName("error/error");

        return mav;
    }
}
