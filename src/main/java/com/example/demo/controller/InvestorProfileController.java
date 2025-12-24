package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.InvestorProfile;
import com.example.demo.service.impl.InvestorProfileServiceImpl;

@RestController
@RequestMapping("/investors")
public class InvestorProfileController {

    private final InvestorProfileServiceImpl service;

    public InvestorProfileController(InvestorProfileServiceImpl service) {
        this.service = service;
    }

    @PostMapping
    public InvestorProfile create(@RequestBody InvestorProfile investor) {
        return service.createInvestor(investor);
    }

    @GetMapping("/{id}")
    public InvestorProfile getById(@PathVariable Long id) {
        return service.getInvestorById(id);
    }

    @GetMapping
    public List<InvestorProfile> getAll() {
        return service.getAllInvestors();
    }
}
