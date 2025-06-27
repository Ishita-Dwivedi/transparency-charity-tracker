package com.transparency;

import java.util.List;
import com.opencsv.CSVReader;
import com.transparency.entity.User;
import com.transparency.entity.Charity;
import com.transparency.entity.Donation;
import com.transparency.repository.UserRepository;
import com.transparency.repository.CharityRepository;
import com.transparency.repository.DonationRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

@Component
public class DataLoader {

    @Autowired
    private CharityRepository charityRepository;

    @PostConstruct
    public void loadCharitiesFromCSV() {
        // ✅ Only run if no charities in DB
        if (charityRepository.count() > 0) {
            System.out.println("⏭️ Charity data already exists. Skipping load.");
            return;
        }

        try (CSVReader csvReader = new CSVReader(
                new BufferedReader(
                        new InputStreamReader(getClass().getResourceAsStream("/data/CLEAN_charity_data.csv"),
                                StandardCharsets.UTF_8)))) {

            String[] tokens;
            csvReader.readNext(); // Skip header row

            while ((tokens = csvReader.readNext()) != null) {
                if (tokens.length < 23) continue;

                String charityName = tokens[13].trim();

                // Optional: double-check for duplicates
                if (!charityRepository.findByName(charityName).isEmpty()) {
                    continue;
                }
                Charity charity = new Charity();
                charity.setName(charityName);
                charity.setDescription(tokens[2].trim());
                charity.setMotto(tokens[14].trim());
                charity.setLeader(tokens[10].trim());
                charity.setState(tokens[16].trim());

                charity.setTotalRevenue(parseDouble(tokens[15]));
                charity.setProgramExpenses(parseDouble(tokens[20]));
                charity.setFundraisingExpenses(parseDouble(tokens[21]));
                charity.setAdministrativeExpenses(parseDouble(tokens[22]));

                charityRepository.save(charity);
            }

            System.out.println("✅ Charity data loaded successfully.");
        } catch (Exception e) {
            System.err.println("❌ Failed to load charity data from CSV:");
            e.printStackTrace();
        }
    }

    private Double parseDouble(String value) {
        try {
            return Double.parseDouble(value.trim());
        } catch (Exception e) {
            return 0.0;
        }
    }
}
