package com.sojung.blog.response;

import com.sojung.blog.util.TimeUtils;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;

public class SuccessResponse extends BaseResponse{

    public SuccessResponse(HttpServletRequest request, Object body){
        setTimestamp(TimeUtils.getCurrentISO8601MilliPlusTime());
        setPath(request.getRequestURI());
        setBody(body);
        setStatus(HttpStatus.OK.value());
    }
}
