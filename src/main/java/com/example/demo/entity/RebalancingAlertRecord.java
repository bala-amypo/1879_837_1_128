package com.example.demo.entity;

import com.example.demo.entity.enums.AssetClassType;
import com.example.demo.entity.enums.AlertSeverity;
import jakarta.persistence.*;
import java.time.LocalDateTime;

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

    public void validate() {
        if (currentPercentage <= targetPercentage) {
            throw new IllegalArgumentException("currentPercentage > targetPercentage required");
        }
    }

    // getters and setters
}