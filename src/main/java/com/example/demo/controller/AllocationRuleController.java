package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.AssetClassAllocationRule;
import com.example.demo.service.impl.AllocationRuleServiceImpl;

@RestController
@RequestMapping("/allocation-rules")
public class AllocationRuleController {

    private final AllocationRuleServiceImpl service;

    public AllocationRuleController(AllocationRuleServiceImpl service) {
        this.service = service;
    }

    @PostMapping
    public AssetClassAllocationRule create(@RequestBody AssetClassAllocationRule rule) {
        return service.createRule(rule);
    }

    @PutMapping("/{id}")
    public AssetClassAllocationRule update(
            @PathVariable Long id,
            @RequestBody AssetClassAllocationRule rule) {
        return service.updateRule(id, rule);
    }

    @GetMapping("/investor/{investorId}")
    public List<AssetClassAllocationRule> getByInvestor(
            @PathVariable Long investorId) {
        return service.getRulesByInvestor(investorId);
    }
}
