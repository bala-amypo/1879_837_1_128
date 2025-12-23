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

    private LocalDateTime snapshotDate = LocalDateTime.now();

    private Double totalPortfolioValue;

    @Lob
    private String allocationJson;


    public Long getId() {
        return id;
    }

    public Long getInvestorId() {
        return investorId;
    }

    public LocalDateTime getSnapshotDate() {
        return snapshotDate;
    }

    public Double getTotalPortfolioValue() {
        return totalPortfolioValue;
    }

    public String getAllocationJson() {
        return allocationJson;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setInvestorId(Long investorId) {
        this.investorId = investorId;
    }

    public void setSnapshotDate(LocalDateTime snapshotDate) {
        this.snapshotDate = snapshotDate;
    }

    public void setTotalPortfolioValue(Double totalPortfolioValue) {
        if (totalPortfolioValue <= 0) {
            throw new IllegalArgumentException("must be > 0");
        }
        this.totalPortfolioValue = totalPortfolioValue;
    }

    public void setAllocationJson(String allocationJson) {
        this.allocationJson = allocationJson;
    }
}
