package com.island.asset.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class ExceptionHandlerController {

    /**
               * 異常處理
     */
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler({Exception.class})
    public Map<String, Object> handleException(HttpServletRequest req, Exception ex) throws Exception {

        logger.error("Request URL : {} , Exception : {}" , req.getRequestURL(), ex.getMessage());

        if(AnnotationUtils.findAnnotation(ex.getClass(), ResponseStatus.class) != null) {
            throw ex;
        }

        Map<String, Object> resultMap = new HashMap<>();
        
        resultMap.put("exception", ex);

        return resultMap;
    }
}
