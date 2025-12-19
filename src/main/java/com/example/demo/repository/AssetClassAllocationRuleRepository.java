package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.AssetClassAllocationRule;

public interface AssetClassAllocationRuleRepository
        extends JpaRepository<AssetClassAllocationRule, Long> {

    List<AssetClassAllocationRule> findByInvestorId(Long investorId);

    @Query("SELECT r FROM AssetClassAllocationRule r WHERE r.investorId = :investorId AND r.active = true")
    List<AssetClassAllocationRule> findActiveRulesHql(Long investorId);
}
