package com.jkl.SistemaBotica.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseData<T> {
    private String status;
    private String message;
    private T data;
}