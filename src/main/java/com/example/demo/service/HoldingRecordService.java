package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.HoldingRecord;

public interface HoldingRecordService {

    HoldingRecord recordHolding(HoldingRecord holding);

    HoldingRecord getHoldingById(Long id);

    List<HoldingRecord> getHoldingsByInvestor(Long investorId);

    List<HoldingRecord> getAllHoldings();
}
