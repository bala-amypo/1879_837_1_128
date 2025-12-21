package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.AllocationSnapshotRecord;

public interface AllocationSnapshotRecordRepository
        extends JpaRepository<AllocationSnapshotRecord, Long> {

    List<AllocationSnapshotRecord> findByInvestorId(Long investorId);
}
