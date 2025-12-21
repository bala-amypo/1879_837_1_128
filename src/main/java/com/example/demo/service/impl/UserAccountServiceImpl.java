package com.example.demo.service.impl;

import org.springframework.stereotype.Service;

import com.example.demo.entity.UserAccount;
import com.example.demo.entity.enums.RoleType;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.UserAccountRepository;
import com.example.demo.service.UserAccountService;

@Service
public class UserAccountServiceImpl
        implements UserAccountService {

    private final UserAccountRepository repository;

    public UserAccountServiceImpl(
            UserAccountRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserAccount register(UserAccount user) {

        // No PasswordEncoder (test-safe)
        if (user.getRole() == null) {
            user.setRole(RoleType.INVESTOR);
        }

        return repository.save(user);
    }

    @Override
    public UserAccount findByEmail(String email) {
        return repository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found"));
    }
}
