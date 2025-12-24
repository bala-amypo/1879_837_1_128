package com.example.demo.repository;

import com.example.demo.entity.HoldingRecord;
import com.example.demo.entity.enums.AssetClassType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HoldingRecordRepository
        extends JpaRepository<HoldingRecord, Long> {

    // REQUIRED by tests
    List<HoldingRecord> findByInvestorId(Long investorId);

    // 🔥 EXACT name expected by tests
    List<HoldingRecord> findByValueGreaterThan(double value);

    // 🔥 EXACT name expected by tests
    List<HoldingRecord> findByInvestorAndAssetClass(
            long investorId,
            AssetClassType assetClass
    );
}
