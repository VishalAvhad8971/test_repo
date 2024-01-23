package com.test.restAPI.Exam.RestAPI.Design.utils;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiResponse {
    private String status;
    private String data;
}
