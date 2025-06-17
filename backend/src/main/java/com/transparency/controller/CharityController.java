package com.transparency.controller;

import com.transparency.entity.Charity;
import com.transparency.service.CharityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/charities")
public class CharityController {

    @Autowired
    private CharityService charityService;

    @GetMapping
    public List<Charity> getAllCharities() {
        return charityService.getAllCharities();
    }

    @GetMapping("/{id}")
    public Charity getCharityById(@PathVariable Long id) {
        return charityService.getCharityById(id);
    }

    @PostMapping
    public Charity createCharity(@RequestBody Charity charity) {
        return charityService.createCharity(charity);
    }
}
