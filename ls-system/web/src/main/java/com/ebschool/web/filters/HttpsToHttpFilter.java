package com.ebschool.web.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * User: michau
 * Date: 9/4/13
 */
@WebFilter(value = "/*")
public class HttpsToHttpFilter implements Filter {

    private boolean toNonSecure;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        String url = request.getRequestURL().toString();
        setToNonSecure(new Boolean(request.getParameter("toNonSecure")));
        // if navigating from secure page to non secure
        if (isToNonSecure()){
            HttpSession session = request.getSession();
            if (session != null) session.invalidate();
            url = url.replace("https://", "http://");
            // TODO: later on get the port number from some configuration store
            url = url.replace("8443","8080");
            response.sendRedirect(url);
        }
        filterChain.doFilter(servletRequest, servletResponse);
        return;
    }

    @Override
    public void destroy() {}

    public boolean isToNonSecure() {
        return toNonSecure;
    }

    public void setToNonSecure(boolean toNonSecure) {
        this.toNonSecure = toNonSecure;
    }
}
