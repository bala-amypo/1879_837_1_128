package com.example.demo.entity;

import java.time.LocalDateTime;

import com.example.demo.entity.enums.AssetClassType;
import com.example.demo.entity.enums.AlertSeverity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;

@Entity
@Table(name = "rebalancing_alerts")
public class RebalancingAlertRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long investorId;

    @Enumerated(EnumType.STRING)
    private AssetClassType assetClass;

    private Double currentPercentage;

    private Double targetPercentage;

    @Enumerated(EnumType.STRING)
    private AlertSeverity severity;

    private String message;

    private LocalDateTime alertDate;

    private Boolean resolved = false;

    // ---------- Validation ----------

    public void validate() {
        if (currentPercentage == null || targetPercentage == null ||
            currentPercentage <= targetPercentage) {
            throw new IllegalArgumentException("currentPercentage > targetPercentage");
        }
    }

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

    public Double getCurrentPercentage() {
        return currentPercentage;
    }

    public Double getTargetPercentage() {
        return targetPercentage;
    }

    public AlertSeverity getSeverity() {
        return severity;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getAlertDate() {
        return alertDate;
    }

    public Boolean getResolved() {
        return resolved;
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

    public void setCurrentPercentage(Double currentPercentage) {
        this.currentPercentage = currentPercentage;
    }

    public void setTargetPercentage(Double targetPercentage) {
        this.targetPercentage = targetPercentage;
    }

    public void setSeverity(AlertSeverity severity) {
        this.severity = severity;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setAlertDate(LocalDateTime alertDate) {
        this.alertDate = alertDate;
    }

    public void setResolved(Boolean resolved) {
        this.resolved = resolved;
    }
}
