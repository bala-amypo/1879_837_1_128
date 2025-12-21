package com.example.demo.entity;

import java.time.LocalDateTime;

import com.example.demo.entity.enums.AssetClassType;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;

@Entity
@Table(name = "holding_records")
public class HoldingRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long investorId;

    @Enumerated(EnumType.STRING)
    private AssetClassType assetClass;

    private Double currentValue;

    private LocalDateTime snapshotDate;

    // ---------- Getters ----------

    public Long getId() {
        return id;
    }

    public Long getInvestorId() {
        return investorId;
    }

    public AssetClassType getAssetClass() {
        return assetClass;
    }

    public Double getCurrentValue() {
        return currentValue;
    }

    public LocalDateTime getSnapshotDate() {
        return snapshotDate;
    }

    // ---------- Setters ----------

    public void setId(Long id) {
        this.id = id;
    }

    public void setInvestorId(Long investorId) {
        this.investorId = investorId;
    }

    public void setAssetClass(AssetClassType assetClass) {
        this.assetClass = assetClass;
    }

    public void setCurrentValue(Double currentValue) {
        if (currentValue == null || currentValue <= 0) {
            throw new IllegalArgumentException("currentValue must be > 0");
        }
        this.currentValue = currentValue;
    }

    public void setSnapshotDate(LocalDateTime snapshotDate) {
        this.snapshotDate = snapshotDate;
    }
}
