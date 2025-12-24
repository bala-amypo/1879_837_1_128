package com.example.demo.entity;

import java.time.LocalDateTime;

import com.example.demo.entity.enums.AssetClassType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

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

    private LocalDateTime lastUpdated;

    public HoldingRecord() {}

    public HoldingRecord(
            Long investorId,
            AssetClassType assetClass,
            Double currentValue,
            LocalDateTime lastUpdated) {

        this.investorId = investorId;
        this.assetClass = assetClass;
        this.currentValue = currentValue;
        this.lastUpdated = lastUpdated;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getInvestorId() { return investorId; }
    public AssetClassType getAssetClass() { return assetClass; }
    public Double getCurrentValue() { return currentValue; }
    public LocalDateTime getLastUpdated() { return lastUpdated; }

    public void setCurrentValue(Double currentValue) {
        this.currentValue = currentValue;
    }
}
