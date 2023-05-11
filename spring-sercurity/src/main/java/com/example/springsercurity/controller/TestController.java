package com.example.springsercurity.controller;

import com.example.springsercurity.dto.response.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/test")
public class TestController {

    @GetMapping("")
    public BaseResponse test() {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setMessage("Da vao duoc");
        baseResponse.setCode(HttpStatus.OK.value());
        return baseResponse;
    }
}
