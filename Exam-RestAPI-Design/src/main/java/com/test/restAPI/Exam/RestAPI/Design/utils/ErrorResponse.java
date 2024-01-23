package com.test.restAPI.Exam.RestAPI.Design.utils;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ErrorResponse {
    private String status;
    private List<String> messages ;
}
