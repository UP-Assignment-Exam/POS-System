package com.example.POS.System.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GlobalListResponse<T> {
    private String message;
    private Integer status;
    private T data;
    private Integer total = 0;
    private Integer pageSize = 0;
    private Integer currentPage = 0;
}

