package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.AssetClassAllocationRule;
import com.example.demo.service.AllocationRuleService;

@RestController
@RequestMapping("/api/allocation-rules")
public class AllocationRuleController {

    private final AllocationRuleService service;

    public AllocationRuleController(AllocationRuleService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<AssetClassAllocationRule> createRule(
            @RequestBody AssetClassAllocationRule rule) {

        return new ResponseEntity<>(
                service.createRule(rule),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/{id}")
    public AssetClassAllocationRule updateRule(
            @PathVariable Long id,
            @RequestBody AssetClassAllocationRule rule) {

        return service.updateRule(id, rule);
    }

    @GetMapping("/{id}")
    public AssetClassAllocationRule getRuleById(
            @PathVariable Long id) {

        return service.getRuleById(id);
    }

    @GetMapping("/investor/{investorId}")
    public List<AssetClassAllocationRule> getRulesByInvestor(
            @PathVariable Long investorId) {

        return service.getRulesByInvestor(investorId);
    }
}
