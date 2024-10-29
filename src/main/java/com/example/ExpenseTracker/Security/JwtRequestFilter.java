package com.example.ExpenseTracker.Security;

import com.example.ExpenseTracker.Exceptions.ErrorResponse;
import com.example.ExpenseTracker.Exceptions.GlobalExceptionHandler;
import com.example.ExpenseTracker.Utility.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Profile("production")
@Component
public class JwtRequestFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;
    private final UserDetailsManagerService userDetailsService;


    public JwtRequestFilter(JwtUtil jwtUtil,
            UserDetailsManagerService userDetailsService) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                    HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String authorizationHeader = request.getHeader("Authorization");
        String username = null;
        String jwt = null;
        
        String path = request.getRequestURI();
        
        if (path.equals("/api/users/login") || path.equals("/api/users/register")) {
            filterChain.doFilter(request, response); 
            return;
        }

        try{
            if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
                jwt = authorizationHeader.substring(7);
                username = jwtUtil.extractUsername(jwt);
            } else {
                throw new GlobalExceptionHandler("Authorization token is missing.",
                        HttpStatus.UNAUTHORIZED,"UNAUTHORIZED");
            }

            if(username != null && SecurityContextHolder.getContext()
                    .getAuthentication() == null){
                UserDetails userDetails = userDetailsService
                        .loadUserByUsername(username);

                if (jwtUtil.isTokenValid(jwt,userDetails.getUsername())){
                    UsernamePasswordAuthenticationToken authenticationToken
                            = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());

                    authenticationToken.setDetails(new
                            WebAuthenticationDetailsSource().buildDetails(request));

                    SecurityContextHolder.getContext()
                            .setAuthentication(authenticationToken);
                } else {
                    throw new GlobalExceptionHandler("Your session has expired or is invalid. Please try to log in again.",
                            HttpStatus.UNAUTHORIZED,"UNAUTHORIZED");
                }
            }

            filterChain.doFilter(request, response);

        }catch(GlobalExceptionHandler ex){
            response.setStatus(ex.getHttpStatus().value());
            response.setContentType("application/json");

            ErrorResponse errorResponse = new
                    ErrorResponse(ex.getError(), ex.getMessage());

            ObjectMapper mapper = new ObjectMapper();
            response.getWriter().write(
                    mapper.writeValueAsString(errorResponse));
        }





    }
}
