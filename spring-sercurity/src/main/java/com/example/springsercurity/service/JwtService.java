package com.example.springsercurity.service;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.Map;

public interface JwtService {

    String getUserNameFromToken(String token);

    String generateToken(Map<String, Object> extraClaims, UserDetails userDetails);

    String generateToken(UserDetails userDetails);

    boolean isValidToken(String token, UserDetails userDetails);

    boolean isTokenExpired(String token);

    Date getExpirationFromToken(String token);
}
