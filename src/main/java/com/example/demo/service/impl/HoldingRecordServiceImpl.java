package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.HoldingRecord;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.HoldingRecordRepository;
import com.example.demo.service.HoldingRecordService;

@Service
public class HoldingRecordServiceImpl implements HoldingRecordService {

    private final HoldingRecordRepository repository;

    public HoldingRecordServiceImpl(
            HoldingRecordRepository repository) {
        this.repository = repository;
    }

    @Override
    public HoldingRecord recordHolding(HoldingRecord holding) {
        if (holding.getCurrentValue() <= 0) {
            throw new IllegalArgumentException("must be > 0");
        }
        return repository.save(holding);
    }

    @Override
    public HoldingRecord getHoldingById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Holding not found"));
    }

    @Override
    public List<HoldingRecord> getHoldingsByInvestor(Long investorId) {
        return repository.findByInvestorId(investorId);
    }

    @Override
    public List<HoldingRecord> getAllHoldings() {
        return repository.findAll();
    }
}
