package com.worktrack.common.infrastructure.web;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;


@Component
public class RequestContextInfo {

    private final HttpServletRequest request;

    public RequestContextInfo(HttpServletRequest request) {
        this.request = request;
    }

    public String getClientIp() {
        String xfHeader = request.getHeader("X-Forwarded-For");
        if (xfHeader != null) {
            return xfHeader.split(",")[0];
        }
        return request.getRemoteAddr();
    }

    public String getUserAgent() {
        return request.getHeader("User-Agent");
    }

    public String getAcceptLanguage() {
        return request.getHeader("Accept-Language");
    }

}
