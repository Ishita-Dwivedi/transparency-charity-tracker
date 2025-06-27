package com.transparency.controller;

import com.transparency.dto.CharityDTO;
import com.transparency.entity.Charity;
import com.transparency.service.CharityService;
import jakarta.validation.Valid;
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
    public Charity createCharity(@RequestBody @Valid CharityDTO charityDTO) {
        // Manually map DTO to Entity
        Charity charity = new Charity();
        charity.setName(charityDTO.getName());
        charity.setDescription(charityDTO.getDescription());
        charity.setMotto(charityDTO.getMotto());
        charity.setLeader(charityDTO.getLeader());
        charity.setState(charityDTO.getState());

        return charityService.createCharity(charity);
    }
}
