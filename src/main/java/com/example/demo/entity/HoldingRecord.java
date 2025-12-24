package com.example.demo.entity;

import com.example.demo.entity.enums.AssetClassType;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class HoldingRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long investorId;

    @Enumerated(EnumType.STRING)
    private AssetClassType assetClass;

    private Double currentValue;
    private LocalDateTime recordedAt;

    public HoldingRecord() {}

    public HoldingRecord(Long investorId, AssetClassType assetClass,
                         Double currentValue, LocalDateTime recordedAt) {
        if (currentValue <= 0) {
            throw new IllegalArgumentException("must be > 0");
        }
        this.investorId = investorId;
        this.assetClass = assetClass;
        this.currentValue = currentValue;
        this.recordedAt = recordedAt;
    }

    public void setId(Long id) { this.id = id; }
    public Long getInvestorId() { return investorId; }
    public Double getCurrentValue() { return currentValue; }
}
