package com.example.demo.entity;

import com.example.demo.entity.enums.AssetClassType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(
    name = "asset_class_allocation_rules",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"investorId", "assetClass"})
    }
)
public class AssetClassAllocationRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long investorId;

    @Enumerated(EnumType.STRING)
    private AssetClassType assetClass;

    private Double targetPercentage;

    private Boolean active = true;

    public AssetClassAllocationRule() {}

    public AssetClassAllocationRule(
            Long investorId,
            AssetClassType assetClass,
            Double targetPercentage,
            Boolean active) {

        this.investorId = investorId;
        this.assetClass = assetClass;
        this.targetPercentage = targetPercentage;
        this.active = active;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getInvestorId() { return investorId; }
    public AssetClassType getAssetClass() { return assetClass; }
    public Double getTargetPercentage() { return targetPercentage; }
    public Boolean getActive() { return active; }

    public void setTargetPercentage(Double targetPercentage) {
        this.targetPercentage = targetPercentage;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
