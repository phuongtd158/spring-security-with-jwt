package com.example.springsercurity.controller;

import com.example.springsercurity.dto.request.LoginRequest;
import com.example.springsercurity.dto.request.RegisterRequest;
import com.example.springsercurity.dto.response.AuthenticationResponse;
import com.example.springsercurity.dto.response.BaseResponse;
import com.example.springsercurity.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<BaseResponse> register(@RequestBody RegisterRequest request) {
        BaseResponse baseResponse = new BaseResponse();
        try {
            AuthenticationResponse register = authenticationService.register(request);
            baseResponse.setCode(HttpStatus.OK.value());
            baseResponse.setData(register);
        } catch (Exception e) {
            baseResponse.setCode(HttpStatus.BAD_REQUEST.value());
            baseResponse.setMessage(e.getMessage());
        }
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<BaseResponse> login(@RequestBody LoginRequest request) {
        BaseResponse baseResponse = new BaseResponse();
        try {
            AuthenticationResponse register = authenticationService.login(request);
            baseResponse.setCode(HttpStatus.OK.value());
            baseResponse.setData(register);
        } catch (Exception e) {
            baseResponse.setCode(HttpStatus.BAD_REQUEST.value());
            baseResponse.setMessage(e.getMessage());
        }
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }
}
