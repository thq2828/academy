package com.academy.core.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class Response<T> implements Serializable {
    private Integer code;
    private String message;
    private T data;

    private static final long serialVersionUID = 1L;

    public Response() {
    }

    public Response(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
