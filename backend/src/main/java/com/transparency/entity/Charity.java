package com.transparency.entity;

import jakarta.persistence.*;
import java.util.List;
import java.time.LocalDateTime;
@Entity
public class Charity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private String registrationNumber;

    private String contactEmail;

    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "charity", cascade = CascadeType.ALL)
    private List<Donation> donations;

    @OneToMany(mappedBy = "charity", cascade = CascadeType.ALL)
    private List<SpendingReport> reports;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public List<Donation> getDonations() {
        return donations;
    }

    public void setDonations(List<Donation> donations) {
        this.donations = donations;
    }

    public List<SpendingReport> getReports() {
        return reports;
    }

    public void setReports(List<SpendingReport> reports) {
        this.reports = reports;
    }

}
