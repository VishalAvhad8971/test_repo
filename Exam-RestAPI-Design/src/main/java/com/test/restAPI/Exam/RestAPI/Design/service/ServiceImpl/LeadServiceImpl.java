package com.test.restAPI.Exam.RestAPI.Design.service.ServiceImpl;

import com.test.restAPI.Exam.RestAPI.Design.exception.LeadAlreadyExistsException;
import com.test.restAPI.Exam.RestAPI.Design.exception.NoLeadFoundException;
import com.test.restAPI.Exam.RestAPI.Design.model.Lead;
import com.test.restAPI.Exam.RestAPI.Design.repository.LeadRepository;
import com.test.restAPI.Exam.RestAPI.Design.service.LeadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LeadServiceImpl implements LeadService {
    private final LeadRepository leadRepository;

    @Autowired
    public LeadServiceImpl(LeadRepository leadRepository) {
        this.leadRepository = leadRepository;
    }

    @Transactional
    public Lead createLead(Lead lead) {
        if (leadRepository.existsByLeadId(lead.getLeadId())) {
            throw new LeadAlreadyExistsException("Lead Already Exists in the database with the lead id");
        }
        return leadRepository.save(lead);
    }



    public List<Lead> getLeadsByMobileNumber(String mobileNumber) {
        List<Lead> leads = leadRepository.findByMobileNumber(mobileNumber);
        if (leads.isEmpty()) {
            throw new NoLeadFoundException("No Lead found with the Mobile Number " + mobileNumber);
        }
        return leads;
    }


}
