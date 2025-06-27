package com.transparency.service;

import com.transparency.dto.CharityDTO;
import com.transparency.entity.Charity;
import java.util.List;

public interface CharityService {
    List<Charity> getAllCharities();
    Charity getCharityById(Long id);
    Charity createCharity(Charity charity);
    Charity updateCharity(Long id, Charity charity);
    void deleteCharity(Long id);
    Charity createCharityFromDto(CharityDTO dto);
}

