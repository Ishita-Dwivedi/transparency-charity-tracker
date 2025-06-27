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
    @Lob
    @Column(length = 5000)
    private String description;
    private String motto;
    private String leader;
    @Lob
    @Column(length = 5000)
    private String state;

    private Double totalRevenue;
    private Double programExpenses;
    private Double fundraisingExpenses;
    private Double administrativeExpenses;

    // == Getters and Setters ==

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

    public String getMotto() {
        return motto;
    }

    public void setMotto(String motto) {
        this.motto = motto;
    }

    public String getLeader() {
        return leader;
    }

    public void setLeader(String leader) {
        this.leader = leader;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Double getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(Double totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    public Double getProgramExpenses() {
        return programExpenses;
    }

    public void setProgramExpenses(Double programExpenses) {
        this.programExpenses = programExpenses;
    }

    public Double getFundraisingExpenses() {
        return fundraisingExpenses;
    }

    public void setFundraisingExpenses(Double fundraisingExpenses) {
        this.fundraisingExpenses = fundraisingExpenses;
    }

    public Double getAdministrativeExpenses() {
        return administrativeExpenses;
    }

    public void setAdministrativeExpenses(Double administrativeExpenses) {
        this.administrativeExpenses = administrativeExpenses;
    }
}

