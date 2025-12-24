package com.example.demo.repository;

import com.example.demo.entity.HoldingRecord;
import com.example.demo.entity.enums.AssetClassType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HoldingRecordRepository
        extends JpaRepository<HoldingRecord, Long> {

    // Used by services / controllers
    List<HoldingRecord> findByInvestorId(Long investorId);

    // 🔥 REQUIRED BY TESTS — but overridden using JPQL
    @Query("SELECT h FROM HoldingRecord h WHERE h.currentValue > :value")
    List<HoldingRecord> findByValueGreaterThan(@Param("value") double value);

    // 🔥 REQUIRED BY TESTS — name does not match field, so override
    @Query("""
        SELECT h FROM HoldingRecord h
        WHERE h.investorId = :investorId
          AND h.assetClass = :assetClass
    """)
    List<HoldingRecord> findByInvestorAndAssetClass(
            @Param("investorId") long investorId,
            @Param("assetClass") AssetClassType assetClass
    );
}
