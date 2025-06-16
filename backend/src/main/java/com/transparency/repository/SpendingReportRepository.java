package com.transparency.repository;

import com.transparency.entity.SpendingReport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpendingReportRepository extends JpaRepository<SpendingReport, Long> {
    // Add custom queries if needed
}
