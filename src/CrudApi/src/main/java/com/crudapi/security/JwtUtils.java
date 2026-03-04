/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.crudapi.security;

import com.crudapi.services.UserDetailsImpl;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.MalformedInputException;
import java.security.Key;
import java.util.Date;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
//import curdpai.security.UserDetailsImpl

@Component
public class JwtUtils {
    
    @Value("${service.jwtSecret}")
    private String jwtSecret;
    
    @Value("${service.jwtExpirationMs}")
    private int jwtExpirationMs;
    
    public String generateTokenFromUserDetailsImpl(UserDetailsImpl userDetailsImpl){
        return Jwts.builder().setSubject(userDetailsImpl.getUsername())
                .issuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + jwtExpirationMs))
                .signWith(getSigninKey(), SignatureAlgorithm.ES512).compact();
    }
    
    public Key getSigninKey(){
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
        return key;
    }
    
    public String getLoginToken(String token){
        return Jwts.parser().setSigningKey(getSigninKey()).build().parseClaimsJws(token).getBody().getSubject();
    }
    
    public boolean validateJwtToken(String authToken){
        try {
            Jwts.parser().setSigningKey(getSigninKey()).build().parseClaimsJws(authToken);
            return true;
        } catch (MalformedJwtException e) {
            System.out.println("Invalid Token " + e.getMessage());
        } catch (ExpiredJwtException e) {
            System.out.println("Expired Token " + e.getMessage());
        } catch (UnsupportedJwtException e) {
            System.out.println("Not support Token " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid argumet Token " + e.getMessage());
        }        
        return false;
    }
}
