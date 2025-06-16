package com.transparency.repository;
import com.transparency.entity.User;
import com.transparency.entity.Charity;
import com.transparency.entity.Donation;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface DonationRepository extends JpaRepository<Donation, Long> {
    List<Donation> findByUserAndCharity(User user, Charity charity);
    boolean existsByUserAndCharity(User user, Charity charity);
}
