package com.transparency.controller;

import com.transparency.dto.CharityDTO;
import com.transparency.entity.Charity;
import com.transparency.service.CharityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private CharityService charityService;

    // Only ADMINs can add a charity
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add-charity")
    public ResponseEntity<?> addCharity(@Valid @RequestBody CharityDTO dto) {
        Charity savedCharity = charityService.createCharityFromDto(dto);
        return ResponseEntity.ok(savedCharity);
    }
}

