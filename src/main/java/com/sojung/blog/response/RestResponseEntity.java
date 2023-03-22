package com.sojung.blog.response;

import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class RestResponseEntity extends ResponseEntity {
    public RestResponseEntity(HttpServletRequest request, Object body){
        super(new SuccessResponse(request, body), HttpStatus.OK);
    }
}
