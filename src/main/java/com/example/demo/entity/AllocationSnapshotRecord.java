package com.example.demo.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name = "allocation_snapshots")
public class AllocationSnapshotRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long investorId;

    private LocalDateTime snapshotDate;

    private Double totalPortfolioValue;

    @Lob
    private String allocationJson;

    public AllocationSnapshotRecord() {}

    public AllocationSnapshotRecord(
            Long investorId,
            LocalDateTime snapshotDate,
            Double totalPortfolioValue,
            String allocationJson) {

        if (totalPortfolioValue <= 0) {
            throw new IllegalArgumentException("totalPortfolioValue must be >= 1");
        }

        this.investorId = investorId;
        this.snapshotDate = snapshotDate;
        this.totalPortfolioValue = totalPortfolioValue;
        this.allocationJson = allocationJson;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getInvestorId() { return investorId; }
    public LocalDateTime getSnapshotDate() { return snapshotDate; }
    public Double getTotalPortfolioValue() { return totalPortfolioValue; }
    public String getAllocationJson() { return allocationJson; }
}
