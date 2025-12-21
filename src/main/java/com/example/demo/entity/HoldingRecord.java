package com.example.demo.entity;

import com.example.demo.entity.enums.AssetClassType;
import jakarta.persistence.*;
import java.time.LocalDateTime;

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

    public void setCurrentValue(Double currentValue) {
        if (currentValue <= 0) {
            throw new IllegalArgumentException("currentValue must be > 0");
        }
        this.currentValue = currentValue;
    }

    // getters and setters
}