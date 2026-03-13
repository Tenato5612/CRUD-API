package com.crudapi.security;
/*
import com.crudapi.services.UserDetailsServiceImpl;
import com.mysql.cj.util.StringUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;
//import crudapi.Services.UserDetaailsImpl;

public class AuthFilterToken extends OncePerRequestFilter{

    @Autowired
    private JwtUtils jwtUtils;
    
    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {      
        try {
            String jwt = getToken(request);
            if(jwt != null && jwtUtils.validateJwtToken(jwt)){
                
                String login = jwtUtils.getLoginToken(jwt);
                //Username or Login;
                UserDetails userDetails = UserDetailsService.loadUserByUsername(login);
                LoginPasswordAuthenticationToken auth = new LoginPasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            }
        } catch (Exception e) {
        } finally{
        
        }                
    }
    
    private String getToken(HttpServletRequest request){
        String headerToken = request.getHeader("Authorization");
         if(StringUtils.hasText(headerToken) && headerToken.startsWith("Bearer")){
            return headerToken.replace("Bearer ","");
        }
        return null;
    }
    
}
*/
