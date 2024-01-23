package com.test.restAPI.Exam.RestAPI.Design.utils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDetails {
    private String code;

    private String[] messages;

    public ErrorDetails(String e10010, String message) {
    }

//    public ErrorDetails(String e10010, String message) {
//    }
    
}
