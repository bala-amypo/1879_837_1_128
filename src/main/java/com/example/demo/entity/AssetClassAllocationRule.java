package com.example.demo.entity;

import com.example.demo.entity.enums.AssetClassType;
import jakarta.persistence.*;

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

    public void setTargetPercentage(Double targetPercentage) {
        if (targetPercentage < 0 || targetPercentage > 100) {
            throw new IllegalArgumentException("targetPercentage must be between 0 and 100");
        }
        this.targetPercentage = targetPercentage;
    }

    // getters and setters
}