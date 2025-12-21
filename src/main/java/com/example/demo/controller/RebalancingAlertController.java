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
import com.example.demo.service.RebalancingAlertService;

@RestController
@RequestMapping("/api/alerts")
public class RebalancingAlertController {

    private final RebalancingAlertService service;

    public RebalancingAlertController(
            RebalancingAlertService service) {
        this.service = service;
    }

    @PostMapping
    public RebalancingAlertRecord createAlert(
            @RequestBody RebalancingAlertRecord alert) {

        return service.createAlert(alert);
    }

    @PutMapping("/{id}/resolve")
    public RebalancingAlertRecord resolveAlert(
            @PathVariable Long id) {

        return service.resolveAlert(id);
    }

    @GetMapping("/{id}")
    public RebalancingAlertRecord getAlertById(
            @PathVariable Long id) {

        return service.getAlertById(id);
    }

    @GetMapping("/investor/{investorId}")
    public List<RebalancingAlertRecord>
            getAlertsByInvestor(
                    @PathVariable Long investorId) {

        return service.getAlertsByInvestor(investorId);
    }

    @GetMapping
    public List<RebalancingAlertRecord> getAllAlerts() {
        return service.getAllAlerts();
    }
}
