package com.transparency;

import com.transparency.entity.*;
import com.transparency.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final CharityRepository charityRepository;
    private final DonationRepository donationRepository;

    public DataInitializer(UserRepository userRepository,
                           CharityRepository charityRepository,
                           DonationRepository donationRepository) {
        this.userRepository = userRepository;
        this.charityRepository = charityRepository;
        this.donationRepository = donationRepository;
    }

    @Override
    public void run(String... args) {
        if (userRepository.count() == 0) {
            User user = new User();
            user.setName("John Doe");
            user.setEmail("john@example.com");
            user.setPassword("123456"); // Hash in production!
            user.setRole(User.Role.DONOR);
            userRepository.save(user);

            Charity charity = new Charity();
            charity.setName("Helping Hands");
            charity.setDescription("Providing aid for underprivileged.");
            charity.setCreatedAt(LocalDateTime.now());
            charityRepository.save(charity);

            Donation donation = new Donation();
            donation.setAmount(500.0);
            donation.setUser(user);
            donation.setCharity(charity);
            donation.setDonatedAt(LocalDateTime.now());
            donationRepository.save(donation);
        }
    }
}
