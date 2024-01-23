package com.test.restAPI.Exam.RestAPI.Design.controller;

import com.test.restAPI.Exam.RestAPI.Design.exception.LeadAlreadyExistsException;
import com.test.restAPI.Exam.RestAPI.Design.exception.NoLeadFoundException;
import com.test.restAPI.Exam.RestAPI.Design.model.Lead;
import com.test.restAPI.Exam.RestAPI.Design.service.LeadService;
import com.test.restAPI.Exam.RestAPI.Design.service.ServiceImpl.LeadServiceImpl;
import com.test.restAPI.Exam.RestAPI.Design.utils.ApiResponse;
import com.test.restAPI.Exam.RestAPI.Design.utils.ErrorDetails;
import com.test.restAPI.Exam.RestAPI.Design.utils.ErrorResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;


import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/leads")
@Validated
public class LeadController {

  private final LeadService leadService;
@Autowired
public LeadController(LeadService leadService) {
        this.leadService = leadService;
    }


 @PostMapping("/createLead")
    public ResponseEntity<?> createLead(@Valid @RequestBody Lead lead) {
        try {
            leadService.createLead(lead);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiResponse("success", "Created Leads Successfully"));
        } catch (LeadAlreadyExistsException e) {
            ErrorResponse errorResponse = new ErrorResponse("E10010", Collections.singletonList(e.getMessage()));
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/mobile/{mobileNumber}")
    public ResponseEntity<?> getLeadsByMobileNumber(@PathVariable String mobileNumber) {
        try {
            List<Lead> leads = leadService.getLeadsByMobileNumber(mobileNumber);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ApiResponse("success", leads.toString()));
        } catch (NoLeadFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse("error", (List<String>) new ErrorDetails("E10011", e.getMessage())));
        }
    }

}
