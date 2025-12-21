package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.AllocationSnapshotRecord;
import com.example.demo.service.AllocationSnapshotService;

@RestController
@RequestMapping("/api/snapshots")
public class AllocationSnapshotController {

    private final AllocationSnapshotService service;

    public AllocationSnapshotController(
            AllocationSnapshotService service) {
        this.service = service;
    }

    @PostMapping("/compute/{investorId}")
    public AllocationSnapshotRecord computeSnapshot(
            @PathVariable Long investorId) {

        return service.computeSnapshot(investorId);
    }

    @GetMapping("/{id}")
    public AllocationSnapshotRecord getSnapshotById(
            @PathVariable Long id) {

        return service.getSnapshotById(id);
    }

    @GetMapping("/investor/{investorId}")
    public List<AllocationSnapshotRecord>
            getSnapshotsByInvestor(
                    @PathVariable Long investorId) {

        return service.getSnapshotsByInvestor(investorId);
    }

    @GetMapping
    public List<AllocationSnapshotRecord> getAllSnapshots() {
        return service.getAllSnapshots();
    }
}
