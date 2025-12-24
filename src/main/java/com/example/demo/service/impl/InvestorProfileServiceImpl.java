package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.InvestorProfile;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.InvestorProfileRepository;

public class InvestorProfileServiceImpl {

    private final InvestorProfileRepository repository;

    public InvestorProfileServiceImpl(InvestorProfileRepository repository) {
        this.repository = repository;
    }

    public InvestorProfile createInvestor(InvestorProfile investor) {
        return repository.save(investor);
    }

    public InvestorProfile getInvestorById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Investor not found with id " + id));
    }

    public List<InvestorProfile> getAllInvestors() {
        return repository.findAll();
    }

    public InvestorProfile updateInvestorStatus(Long id, boolean active) {
        InvestorProfile investor = getInvestorById(id);
        investor.setActive(active);
        return repository.save(investor);
    }

    public Optional<InvestorProfile> findByInvestorId(String investorId) {
        return repository.findByInvestorId(investorId);
    }
}
