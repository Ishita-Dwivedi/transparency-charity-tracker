package com.transparency.repository;

import com.transparency.entity.Charity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface CharityRepository extends JpaRepository<Charity, Long> {
    List<Charity> findByName(String name); // ✅ handles multiple results safely

}
