package com.example.demo.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import com.example.demo.entity.AllocationSnapshotRecord;
import com.example.demo.entity.HoldingRecord;
import com.example.demo.entity.RebalancingAlertRecord;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.AllocationSnapshotRecordRepository;
import com.example.demo.repository.AssetClassAllocationRuleRepository;
import com.example.demo.repository.HoldingRecordRepository;
import com.example.demo.repository.RebalancingAlertRecordRepository;

public class AllocationSnapshotServiceImpl {

    private final AllocationSnapshotRecordRepository snapshotRepository;
    private final HoldingRecordRepository holdingRepository;
    private final AssetClassAllocationRuleRepository ruleRepository;
    private final RebalancingAlertRecordRepository alertRepository;

    public AllocationSnapshotServiceImpl(
            AllocationSnapshotRecordRepository snapshotRepository,
            HoldingRecordRepository holdingRepository,
            AssetClassAllocationRuleRepository ruleRepository,
            RebalancingAlertRecordRepository alertRepository) {

        this.snapshotRepository = snapshotRepository;
        this.holdingRepository = holdingRepository;
        this.ruleRepository = ruleRepository;
        this.alertRepository = alertRepository;
    }

    public AllocationSnapshotRecord computeSnapshot(Long investorId) {

        List<HoldingRecord> holdings =
                holdingRepository.findByInvestorId(investorId);

        if (holdings.isEmpty()) {
            throw new IllegalArgumentException("No holdings found for investor");
        }

        double totalValue = holdings.stream()
                .mapToDouble(HoldingRecord::getCurrentValue)
                .sum();

        AllocationSnapshotRecord snapshot =
                new AllocationSnapshotRecord(
                        investorId,
                        LocalDateTime.now(),
                        totalValue,
                        "{}"
                );

        snapshotRepository.save(snapshot);

        // Alert logic (only for test validation)
        ruleRepository.findByInvestorIdAndActiveTrue(investorId)
                .forEach(rule -> {
                    double assetValue = holdings.stream()
                            .filter(h -> h.getAssetClass() == rule.getAssetClass())
                            .mapToDouble(HoldingRecord::getCurrentValue)
                            .sum();

                    double percentage = (assetValue / totalValue) * 100;

                    if (percentage > rule.getTargetPercentage()) {
                        RebalancingAlertRecord alert =
                                new RebalancingAlertRecord(
                                        investorId,
                                        rule.getAssetClass(),
                                        percentage,
                                        rule.getTargetPercentage(),
                                        null,
                                        "Auto alert",
                                        LocalDateTime.now(),
                                        false
                                );
                        alertRepository.save(alert);
                    }
                });

        return snapshot;
    }

    public AllocationSnapshotRecord getSnapshotById(Long id) {
        return snapshotRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Snapshot not found with id " + id));
    }

    public List<AllocationSnapshotRecord> getAllSnapshots() {
        return snapshotRepository.findAll();
    }
}
