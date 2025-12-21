package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.InvestorProfile;

public interface InvestorProfileService {

    InvestorProfile createInvestor(InvestorProfile investor);

    InvestorProfile getInvestorById(Long id);

    List<InvestorProfile> getAllInvestors();
}
