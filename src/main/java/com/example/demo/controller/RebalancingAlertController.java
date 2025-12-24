package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.RebalancingAlertRecord;
import com.example.demo.service.impl.RebalancingAlertServiceImpl;

@RestController
@RequestMapping("/alerts")
public class RebalancingAlertController {

    private final RebalancingAlertServiceImpl service;

    public RebalancingAlertController(RebalancingAlertServiceImpl service) {
        this.service = service;
    }

    @PostMapping
    public RebalancingAlertRecord create(@RequestBody RebalancingAlertRecord alert) {
        return service.createAlert(alert);
    }

    @PutMapping("/{id}/resolve")
    public RebalancingAlertRecord resolve(@PathVariable Long id) {
        return service.resolveAlert(id);
    }

    @GetMapping("/investor/{investorId}")
    public List<RebalancingAlertRecord> getByInvestor(
            @PathVariable Long investorId) {
        return service.getAlertsByInvestor(investorId);
    }
}
