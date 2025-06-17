package com.transparency.service;

import com.transparency.entity.Donation;
import java.util.List;

public interface DonationService {
    List<Donation> getAllDonations();
    Donation getDonationById(Long id);
    Donation createDonation(Donation donation);
    Donation updateDonation(Long id, Donation donation);
    void deleteDonation(Long id);
}

