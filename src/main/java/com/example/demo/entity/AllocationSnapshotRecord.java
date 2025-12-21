package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

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

    public void setTotalPortfolioValue(Double totalPortfolioValue) {
        if (totalPortfolioValue <= 0) {
            throw new IllegalArgumentException("totalPortfolioValue must be > 0");
        }
        this.totalPortfolioValue = totalPortfolioValue;
    }

    // getters and setters
}