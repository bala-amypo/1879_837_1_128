package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.HoldingRecord;
import com.example.demo.service.HoldingRecordService;

@RestController
@RequestMapping("/api/holdings")
public class HoldingRecordController {

    private final HoldingRecordService service;

    public HoldingRecordController(HoldingRecordService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<HoldingRecord> recordHolding(
            @RequestBody HoldingRecord holding) {

        return new ResponseEntity<>(
                service.recordHolding(holding),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/{id}")
    public HoldingRecord getHoldingById(
            @PathVariable Long id) {

        return service.getHoldingById(id);
    }

    @GetMapping("/investor/{investorId}")
    public List<HoldingRecord> getHoldingsByInvestor(
            @PathVariable Long investorId) {

        return service.getHoldingsByInvestor(investorId);
    }

    @GetMapping
    public List<HoldingRecord> getAllHoldings() {
        return service.getAllHoldings();
    }
}
