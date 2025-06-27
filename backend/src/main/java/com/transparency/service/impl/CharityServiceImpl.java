package com.transparency.service.impl;

import com.transparency.dto.CharityDTO;
import com.transparency.entity.Charity;
import com.transparency.repository.CharityRepository;
import com.transparency.service.CharityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.transparency.exception.CharityNotFoundException;

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
        return charityRepository.findById(id)
                .orElseThrow(() -> new CharityNotFoundException("Charity not found with id: " + id));
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

    @Override
    public Charity createCharityFromDto(CharityDTO dto) {
        Charity charity = new Charity();
        charity.setName(dto.getName());
        charity.setDescription(dto.getDescription());
        charity.setMotto(dto.getMotto());
        charity.setLeader(dto.getLeader());
        charity.setState(dto.getState());
        charity.setTotalRevenue(dto.getTotalRevenue());
        charity.setProgramExpenses(dto.getProgramExpenses());
        charity.setFundraisingExpenses(dto.getFundraisingExpenses());
        charity.setAdministrativeExpenses(dto.getAdministrativeExpenses());

        return charityRepository.save(charity);
    }
}
