package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.AllocationSnapshotRecord;
import com.example.demo.service.impl.AllocationSnapshotServiceImpl;

@RestController
@RequestMapping("/snapshots")
public class AllocationSnapshotController {

    private final AllocationSnapshotServiceImpl service;

    public AllocationSnapshotController(AllocationSnapshotServiceImpl service) {
        this.service = service;
    }

    @PostMapping("/{investorId}")
    public AllocationSnapshotRecord compute(@PathVariable Long investorId) {
        return service.computeSnapshot(investorId);
    }

    @GetMapping
    public List<AllocationSnapshotRecord> getAll() {
        return service.getAllSnapshots();
    }
}
