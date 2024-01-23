package com.test.restAPI.Exam.RestAPI.Design.service;

import com.test.restAPI.Exam.RestAPI.Design.model.Lead;

import java.util.List;

public interface LeadService {
    public Lead createLead(Lead lead);

    public List<Lead> getLeadsByMobileNumber(String mobileNumber);

}
