package com.example.demo.service.impl;

import com.example.demo.entity.AssetClassAllocationRule;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.AssetClassAllocationRuleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AllocationRuleServiceImpl {

    private final AssetClassAllocationRuleRepository repository;

    public AllocationRuleServiceImpl(AssetClassAllocationRuleRepository repository) {
        this.repository = repository;
    }

    public AssetClassAllocationRule createRule(AssetClassAllocationRule rule) {
        validate(rule.getTargetPercentage());
        return repository.save(rule);
    }

    public AssetClassAllocationRule updateRule(Long id, AssetClassAllocationRule updated) {
        AssetClassAllocationRule existing =
                repository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException("Rule not found with id " + id));

        validate(updated.getTargetPercentage());
        existing.setTargetPercentage(updated.getTargetPercentage());
        existing.setActive(updated.getActive());
        return repository.save(existing);
    }

    public List<AssetClassAllocationRule> getRulesByInvestor(Long investorId) {
        return repository.findByInvestorId(investorId);
    }

    private void validate(Double value) {
        if (value < 0 || value > 100) {
            throw new IllegalArgumentException("targetPercentage must be between 0 and 100");
        }
    }
}
