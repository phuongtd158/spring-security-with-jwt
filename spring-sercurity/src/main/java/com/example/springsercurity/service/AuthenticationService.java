package com.example.springsercurity.service;

import com.example.springsercurity.dto.request.LoginRequest;
import com.example.springsercurity.dto.request.RegisterRequest;
import com.example.springsercurity.dto.response.AuthenticationResponse;

public interface AuthenticationService {

    AuthenticationResponse register(RegisterRequest request) throws IllegalAccessException;
    AuthenticationResponse login(LoginRequest request) throws IllegalAccessException;

}
