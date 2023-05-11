package com.example.springsercurity.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class BaseResponse {
    private Integer code;
    private String message;
    private Object data;
}
