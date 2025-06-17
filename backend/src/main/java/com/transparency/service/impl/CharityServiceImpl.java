package com.transparency.service.impl;

import com.transparency.entity.Charity;
import com.transparency.repository.CharityRepository;
import com.transparency.service.CharityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CharityServiceImpl implements CharityService {

    @Autowired
    private CharityRepository charityRepository;

    @Override
    public List<Charity> getAllCharities() {
        return charityRepository.findAll();
    }

    @Override
    public Charity getCharityById(Long id) {
        return charityRepository.findById(id).orElse(null);
    }

    @Override
    public Charity createCharity(Charity charity) {
        return charityRepository.save(charity);
    }

    @Override
    public Charity updateCharity(Long id, Charity charity) {
        Charity existing = charityRepository.findById(id).orElse(null);
        if (existing == null) return null;

        existing.setName(charity.getName());
        existing.setDescription(charity.getDescription());
        // Add other fields as needed
        return charityRepository.save(existing);
    }

    @Override
    public void deleteCharity(Long id) {
        charityRepository.deleteById(id);
    }
}
