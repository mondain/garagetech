package com.lewdlistings.web.filter;

import com.lewdlistings.entity.User;
import com.lewdlistings.service.UserService;
import com.lewdlistings.web.util.UserThreadLocal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;


public class CurrentUserProcessingFilter implements Filter, InitializingBean {

    private static final Logger logger = LoggerFactory.getLogger(CurrentUserProcessingFilter.class);

    private UserService service;

    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated()) {
            Object principal = auth.getPrincipal();
            if (principal instanceof UserDetails) {
                String username = ((UserDetails) principal).getUsername();
                logger.debug("Processing current user: {}", username);
                User user = service.findByUsername(username);
                if (user != null) {
                    UserThreadLocal.setCurrentUser(user);
                    request.setAttribute("currentUser", user);
                }
            }
        }
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

    public void afterPropertiesSet() {
        Assert.notNull(service, "UserService must be specified.");
    }

    @Required
    public void setService(UserService service) {
        this.service = service;
    }
}