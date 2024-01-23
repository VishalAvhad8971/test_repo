package com.test.restAPI.Exam.RestAPI.Design;

import com.test.restAPI.Exam.RestAPI.Design.controller.LeadController;
import com.test.restAPI.Exam.RestAPI.Design.exception.LeadAlreadyExistsException;
import com.test.restAPI.Exam.RestAPI.Design.model.Lead;
import com.test.restAPI.Exam.RestAPI.Design.service.LeadService;
import com.test.restAPI.Exam.RestAPI.Design.utils.ApiResponse;
import com.test.restAPI.Exam.RestAPI.Design.utils.ErrorResponse;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class LeadControllerCreateTest {
    @Mock
    private LeadService leadService;

    @InjectMocks
    private LeadController leadController;

    @Test
    void createLead_WithValidLeadData_ReturnsSuccessResponse() {
        // Arrange
        Lead lead = new Lead();
        lead.setLeadId(5678);
        lead.setFirstName("Vineet");
        lead.setLastName("KV");
        lead.setMobileNumber("8877887788");
        lead.setGender("Male");
        lead.setDob(new Date());
        lead.setEmail("v@gmail.com");

        when(leadService.createLead(any(Lead.class))).thenReturn(lead);

        // Act
        ResponseEntity<?> responseEntity = leadController.createLead(lead);

        // Assert
        verify(leadService, times(1)).createLead(any(Lead.class));
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals("success", ((ApiResponse) responseEntity.getBody()).getStatus());
        assertEquals("Created Leads Successfully", ((ApiResponse) responseEntity.getBody()).getData());
    }

    @Test
    void createLead_WithDuplicateLeadId_ReturnsErrorResponse() {
        // Arrange
        Lead lead = new Lead();
        lead.setLeadId(5678);
        lead.setFirstName("Vineet");
        lead.setLastName("KV");
        lead.setMobileNumber("8877887788");
        lead.setGender("Male");
        lead.setDob(new Date());
        lead.setEmail("v@gmail.com");

        when(leadService.createLead(any(Lead.class))).thenThrow(new LeadAlreadyExistsException("Lead Already Exists in the database with the lead id"));

        // Act
        ResponseEntity<?> responseEntity = leadController.createLead(lead);

        // Assert
        verify(leadService, times(1)).createLead(any(Lead.class));
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("error", ((ErrorResponse) responseEntity.getBody()).getStatus());
        assertEquals("E10010", ((ErrorResponse) responseEntity.getBody()).getMessages().hashCode());
        assertEquals("Lead Already Exists in the database with the lead id", ((ErrorResponse) responseEntity.getBody()));

    }
}