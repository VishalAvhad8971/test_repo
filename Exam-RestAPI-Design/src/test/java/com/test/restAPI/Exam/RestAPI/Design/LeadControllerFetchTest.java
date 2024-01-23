package com.test.restAPI.Exam.RestAPI.Design;
import com.test.restAPI.Exam.RestAPI.Design.controller.LeadController;
import com.test.restAPI.Exam.RestAPI.Design.exception.NoLeadFoundException;
import com.test.restAPI.Exam.RestAPI.Design.model.Lead;
import com.test.restAPI.Exam.RestAPI.Design.service.LeadService;
import com.test.restAPI.Exam.RestAPI.Design.utils.ApiResponse;
import com.test.restAPI.Exam.RestAPI.Design.utils.ErrorResponse;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class LeadControllerFetchTest{

    @Mock
    private LeadService leadService;

    @InjectMocks
    private LeadController leadController;

    @Test
    void getLeadsByMobileNumber_WithValidMobileNumber_ReturnsSuccessResponse() {
        // demo data for the test
        String mobileNumber = "8877887788";
        Lead lead = new Lead();
        lead.setLeadId(5678);
        lead.setFirstName("Vineet");
        lead.setLastName("KV");
        lead.setMobileNumber(mobileNumber);
        lead.setGender("Male");
        lead.setDob(new Date());
        lead.setEmail("v@gmail.com");

        List<Lead> leads = Collections.singletonList(lead);
        when(leadService.getLeadsByMobileNumber(anyString())).thenReturn(leads);

        // Act
        ResponseEntity<?> responseEntity = leadController.getLeadsByMobileNumber(mobileNumber);

        // Assert
        verify(leadService, times(1)).getLeadsByMobileNumber(anyString());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("success", ((ApiResponse) responseEntity.getBody()).getStatus());
        assertEquals(leads, ((ApiResponse) responseEntity.getBody()).getData());
    }

    @Test
    void getLeadsByMobileNumber_WithInvalidMobileNumber_ReturnsErrorResponse() {
        // Arrange
        String mobileNumber = "9999999999";
        when(leadService.getLeadsByMobileNumber(anyString())).thenThrow(new NoLeadFoundException("No Lead found with the Mobile Number " + mobileNumber));

        // Act
        ResponseEntity<?> responseEntity = leadController.getLeadsByMobileNumber(mobileNumber);

        // Assert
        verify(leadService, times(1)).getLeadsByMobileNumber(anyString());
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals("error", ((ErrorResponse) responseEntity.getBody()).getStatus());
        assertEquals("E10011", ((ErrorResponse) responseEntity.getBody()).getMessages());
        assertEquals("No Lead found with the Mobile Number " + mobileNumber, ((ErrorResponse) responseEntity.getBody()));
    }
}
