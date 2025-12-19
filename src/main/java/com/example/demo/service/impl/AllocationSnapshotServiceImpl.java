package com.example.demo.service.impl;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.demo.entity.*;
import com.example.demo.entity.enums.AlertSeverity;
import com.example.demo.repository.*;
import com.example.demo.service.AllocationSnapshotService;

public class AllocationSnapshotServiceImpl implements AllocationSnapshotService {

    private final AllocationSnapshotRecordRepository snapshotRepo;
    private final HoldingRecordRepository holdingRepo;
    private final AssetClassAllocationRuleRepository ruleRepo;
    private final RebalancingAlertRecordRepository alertRepo;

    // ⚠️ DO NOT CHANGE ORDER
    public AllocationSnapshotServiceImpl(
            AllocationSnapshotRecordRepository snapshotRepo,
            HoldingRecordRepository holdingRepo,
            AssetClassAllocationRuleRepository ruleRepo,
            RebalancingAlertRecordRepository alertRepo) {

        this.snapshotRepo = snapshotRepo;
        this.holdingRepo = holdingRepo;
        this.ruleRepo = ruleRepo;
        this.alertRepo = alertRepo;
    }

    @Override
    public AllocationSnapshotRecord computeSnapshot(Long investorId) {

        List<HoldingRecord> holdings = holdingRepo.findByInvestorId(investorId);
        if (holdings.isEmpty()) {
            throw new IllegalArgumentException("No holdings");
        }

        double total = holdings.stream()
                .mapToDouble(HoldingRecord::getCurrentValue)
                .sum();

        if (total <= 0) {
            throw new IllegalArgumentException("must be > 0");
        }

        Map<String, Double> allocation = new HashMap<>();

        for (HoldingRecord h : holdings) {
            allocation.merge(
                    h.getAssetClass().name(),
                    h.getCurrentValue(),
                    Double::sum
            );
        }

        for (String key : allocation.keySet()) {
            allocation.put(key, (allocation.get(key) / total) * 100);
        }

        AllocationSnapshotRecord snapshot = new AllocationSnapshotRecord();
        snapshot.setInvestorId(investorId);
        snapshot.setSnapshotDate(LocalDateTime.now());
        snapshot.setTotalPortfolioValue(total);
        snapshot.setAllocationJson(allocation.toString());

        snapshotRepo.save(snapshot);

        List<AssetClassAllocationRule> rules = ruleRepo.findActiveRulesHql(investorId);

        for (AssetClassAllocationRule rule : rules) {
            Double currentPct = allocation.get(rule.getAssetClass().name());
            if (currentPct != null && currentPct > rule.getTargetPercentage()) {

                RebalancingAlertRecord alert = new RebalancingAlertRecord();
                alert.setInvestorId(investorId);
                alert.setAssetClass(rule.getAssetClass());
                alert.setCurrentPercentage(currentPct);
                alert.setTargetPercentage(rule.getTargetPercentage());
                alert.setSeverity(AlertSeverity.HIGH);
                alert.setMessage("Rebalancing required");
                alert.setAlertDate(LocalDateTime.now());
                alert.setResolved(false);

                alertRepo.save(alert);
            }
        }

        return snapshot;
    }

    @Override
    public AllocationSnapshotRecord getSnapshotById(Long id) {
        return snapshotRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Snapshot not found"));
    }

    @Override
    public List<AllocationSnapshotRecord> getSnapshotsByInvestor(Long investorId) {
        return snapshotRepo.findAll();
    }

    @Override
    public List<AllocationSnapshotRecord> getAllSnapshots() {
        return snapshotRepo.findAll();
    }
}
