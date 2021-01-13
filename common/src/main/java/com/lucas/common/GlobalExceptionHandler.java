package com.lucas.common;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lucas.util.IotUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

/**
 * @author: lucasfen
 * @create: 2021/01/11
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public void defaultErrorHandler(HttpServletRequest req, Exception e) {
        String ipAddr = IotUtil.getIpAddrFromReq(req);
        log.error("failed to handler request. client IP: {}. uri: {}, Method: {}, headers: {}",
                ipAddr, req.getRequestURI(), req.getMethod(), getHeaders(req), e);

    }

    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public void badRequestHandler(HttpServletResponse response, HttpServletRequest req,
                                  HttpMessageNotReadableException e) throws IOException {
        String ipAddr = IotUtil.getIpAddrFromReq(req);
        log.error("failed to handler request. client IP: {}. uri: {}, Method: {}, headers: {}",
                ipAddr, req.getRequestURI(), req.getMethod(), getHeaders(req), e);
        response.sendError(HttpStatus.BAD_REQUEST.value());
    }

    /**
     * 上传文件大小超过限制
     */
    @ExceptionHandler(value = MaxUploadSizeExceededException.class)
    public void fileSizeLimitedHandler(HttpServletRequest req, MaxUploadSizeExceededException e) {
        String ipAddr = IotUtil.getIpAddrFromReq(req);
        log.error("failed to handler request. client IP: {}. uri: {}, Method: {}, headers: {}",
                ipAddr, req.getRequestURI(), req.getMethod(), getHeaders(req), e);
    }

    private Map<String, String> getHeaders(HttpServletRequest request) {
        Map<String, String> headers = new HashMap<>();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            headers.put(headerName, request.getHeader(headerName));
        }
        return headers;
    }
}
