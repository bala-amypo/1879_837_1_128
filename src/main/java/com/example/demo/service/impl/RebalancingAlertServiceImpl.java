package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.RebalancingAlertRecord;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.RebalancingAlertRecordRepository;
import com.example.demo.service.RebalancingAlertService;

@Service
public class RebalancingAlertServiceImpl
        implements RebalancingAlertService {

    private final RebalancingAlertRecordRepository repository;

    public RebalancingAlertServiceImpl(
            RebalancingAlertRecordRepository repository) {
        this.repository = repository;
    }

    @Override
    public RebalancingAlertRecord createAlert(
            RebalancingAlertRecord alert) {

        if (alert.getCurrentPercentage()
                <= alert.getTargetPercentage()) {
            throw new IllegalArgumentException(
                    "currentPercentage > targetPercentage");
        }

        return repository.save(alert);
    }

    @Override
    public RebalancingAlertRecord resolveAlert(Long id) {

        RebalancingAlertRecord alert =
                repository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Alert not found"));

        alert.setResolved(true);
        return repository.save(alert);
    }

    @Override
    public RebalancingAlertRecord getAlertById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Alert not found"));
    }

    @Override
    public List<RebalancingAlertRecord>
            getAlertsByInvestor(Long investorId) {

        return repository.findByInvestorId(investorId);
    }

    @Override
    public List<RebalancingAlertRecord> getAllAlerts() {
        return repository.findAll();
    }
}
