package com.sojung.blog.response;

import com.sojung.blog.util.TimeUtils;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;

public class ErrorResponse extends BaseResponse{
    public ErrorResponse(HttpStatus httpStatus, HttpServletRequest request, String message){
        setTimestamp(TimeUtils.getCurrentISO8601MilliPlusTime());
        setPath(request.getRequestURI());
        setMessage(message);
        setStatus(httpStatus.value());
        setError(httpStatus.getReasonPhrase());
    }

}
