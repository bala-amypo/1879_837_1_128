package com.example.demo.service.impl;

import java.util.List;

import com.example.demo.entity.AssetClassAllocationRule;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.AssetClassAllocationRuleRepository;

public class AllocationRuleServiceImpl {

    private final AssetClassAllocationRuleRepository repository;

    public AllocationRuleServiceImpl(AssetClassAllocationRuleRepository repository) {
        this.repository = repository;
    }

    public AssetClassAllocationRule createRule(AssetClassAllocationRule rule) {
        validatePercentage(rule.getTargetPercentage());
        return repository.save(rule);
    }

    public AssetClassAllocationRule updateRule(Long id, AssetClassAllocationRule updated) {
        AssetClassAllocationRule existing =
                repository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException("Rule not found with id " + id));

        validatePercentage(updated.getTargetPercentage());
        existing.setTargetPercentage(updated.getTargetPercentage());
        existing.setActive(updated.getActive());

        return repository.save(existing);
    }

    public List<AssetClassAllocationRule> getRulesByInvestor(Long investorId) {
        return repository.findByInvestorId(investorId);
    }

    private void validatePercentage(Double value) {
        if (value < 0 || value > 100) {
            throw new IllegalArgumentException(
                    "targetPercentage must be between 0 and 100");
        }
    }
}
