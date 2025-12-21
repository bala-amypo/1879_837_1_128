package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.RebalancingAlertRecord;

public interface RebalancingAlertService {

    RebalancingAlertRecord createAlert(RebalancingAlertRecord alert);

    RebalancingAlertRecord resolveAlert(Long id);

    RebalancingAlertRecord getAlertById(Long id);

    List<RebalancingAlertRecord> getAlertsByInvestor(Long investorId);

    List<RebalancingAlertRecord> getAllAlerts();
}
