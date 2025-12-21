package com.example.demo.entity;

import com.example.demo.entity.enums.AssetClassType;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;

@Entity
@Table(name = "allocation_rules")
public class AssetClassAllocationRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long investorId;

    @Enumerated(EnumType.STRING)
    private AssetClassType assetClass;

    private Double targetPercentage;

    private Boolean active = true;

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

    public Double getTargetPercentage() {
        return targetPercentage;
    }

    public Boolean getActive() {
        return active;
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

    public void setTargetPercentage(Double targetPercentage) {
        if (targetPercentage == null || targetPercentage < 0 || targetPercentage > 100) {
            throw new IllegalArgumentException("targetPercentage must be between 0 and 100");
        }
        this.targetPercentage = targetPercentage;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
