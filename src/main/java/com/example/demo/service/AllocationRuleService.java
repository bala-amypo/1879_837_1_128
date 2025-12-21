package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.AssetClassAllocationRule;

public interface AllocationRuleService {

    AssetClassAllocationRule createRule(AssetClassAllocationRule rule);

    AssetClassAllocationRule updateRule(Long id, AssetClassAllocationRule rule);

    AssetClassAllocationRule getRuleById(Long id);

    List<AssetClassAllocationRule> getRulesByInvestor(Long investorId);

    List<AssetClassAllocationRule> getActiveRules(Long investorId);
}
