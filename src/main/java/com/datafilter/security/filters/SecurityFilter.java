package com.datafilter.security.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

public class SecurityFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        if (httpServletRequest.getHeader("security") != null) {
            httpServletResponse.reset();
            httpServletResponse.sendError(403, "Forbidden : this headers not allowed");
            chain.doFilter(request, response);
            return;
        }
        httpServletResponse.setHeader("security", UUID.randomUUID().toString());
        chain.doFilter(request, response);
    }
}
