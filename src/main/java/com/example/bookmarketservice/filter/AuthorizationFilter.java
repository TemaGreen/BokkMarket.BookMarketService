package com.example.bookmarketservice.filter;

import com.example.bookmarketservice.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class AuthorizationFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Value("${auth.enabled}")
    private boolean enabled;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        if(!enabled){
            filterChain.doFilter(request, response);
        }
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if(header == null || header.isBlank())
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        else if(!checkAuthorization(header))
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        else filterChain.doFilter(request, response);
    }

    private boolean checkAuthorization(String auth){
        if(!auth.startsWith("Bearer "))
            return false;
        String token = auth.substring(7);
        return tokenService.checkToken(token);
    }

    public AuthorizationFilter(TokenService tokenService, boolean enabled) {
        this.tokenService = tokenService;
        this.enabled = enabled;
    }

    public AuthorizationFilter(TokenService tokenService) {
        this.tokenService = tokenService;
        this.enabled = false;
    }

    public AuthorizationFilter() {
    }
}
