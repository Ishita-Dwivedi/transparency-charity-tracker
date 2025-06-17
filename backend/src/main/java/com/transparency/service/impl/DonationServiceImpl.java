package com.transparency.service.impl;

import com.transparency.entity.Donation;
import com.transparency.repository.DonationRepository;
import com.transparency.service.DonationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DonationServiceImpl implements DonationService {

    @Autowired
    private DonationRepository donationRepository;

    @Override
    public List<Donation> getAllDonations() {
        return donationRepository.findAll();
    }

    @Override
    public Donation getDonationById(Long id) {
        return donationRepository.findById(id).orElse(null);
    }

    @Override
    public Donation createDonation(Donation donation) {
        return donationRepository.save(donation);
    }

    @Override
    public Donation updateDonation(Long id, Donation donation) {
        Donation existing = donationRepository.findById(id).orElse(null);
        if (existing == null) return null;

        existing.setAmount(donation.getAmount());
        existing.setUser(donation.getUser());
        existing.setCharity(donation.getCharity()); // Assuming there's a Charity field
        existing.setDonatedAt(donation.getDonatedAt());
        return donationRepository.save(existing);
    }

    @Override
    public void deleteDonation(Long id) {
        donationRepository.deleteById(id);
    }
}

