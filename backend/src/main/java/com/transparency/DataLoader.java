package com.transparency;

import java.util.List;

import com.transparency.entity.User;
import com.transparency.entity.Charity;
import com.transparency.entity.Donation;
import com.transparency.repository.UserRepository;
import com.transparency.repository.CharityRepository;
import com.transparency.repository.DonationRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner loadData(UserRepository userRepo, CharityRepository charityRepo, DonationRepository donationRepo) {
        return args -> {
            // Check if user already exists
            User user = userRepo.findByEmail("ishita@example.com").orElse(null);
            if (user == null) {
                user = new User();
                user.setName("Ishita");
                user.setEmail("ishita@example.com");
                user.setPassword("secret123");
                user = userRepo.save(user);
            }

            List<Charity> found = charityRepo.findByName("Helping Hands");
            Charity charity;

            if (found.isEmpty()) {
                charity = new Charity();
                charity.setName("Helping Hands");
                charity.setDescription("Supporting underprivileged communities.");
                charity = charityRepo.save(charity);
            } else {
                charity = found.get(0); // Just pick the first one
            }


            // Check for existing donations
            List<Donation> existing = donationRepo.findByUserAndCharity(user, charity);
            if (existing.isEmpty()) {
                Donation donation = new Donation();
                donation.setUser(user);
                donation.setCharity(charity);
                donation.setAmount(500.0);
                donationRepo.save(donation);
            }
        };
    }
}
