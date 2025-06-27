package com.transparency.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;

public class CharityDTO {

    @NotBlank(message = "Name is required")
    @Size(max = 255)
    private String name;

    @Size(max = 5000, message = "Description is too long")
    private String description;

    @Size(max = 255)
    private String motto;

    @Size(max = 255)
    private String leader;

    private String state;

    @NotNull
    private Double totalRevenue;
    private Double programExpenses;
    private Double fundraisingExpenses;
    private Double administrativeExpenses;

    // Getters
    public String getName() { return name; }
    public String getDescription() { return description; }
    public String getMotto() { return motto; }
    public String getLeader() { return leader; }
    public String getState() { return state; }
    public Double getTotalRevenue() { return totalRevenue; }
    public Double getProgramExpenses() { return programExpenses; }
    public Double getFundraisingExpenses() { return fundraisingExpenses; }
    public Double getAdministrativeExpenses() { return administrativeExpenses; }

    // Setters
    public void setName(String name) { this.name = name; }
    public void setDescription(String description) { this.description = description; }
    public void setMotto(String motto) { this.motto = motto; }
    public void setLeader(String leader) { this.leader = leader; }
    public void setState(String state) { this.state = state; }
    public void setTotalRevenue(Double totalRevenue) { this.totalRevenue = totalRevenue; }
    public void setProgramExpenses(Double programExpenses) { this.programExpenses = programExpenses; }
    public void setFundraisingExpenses(Double fundraisingExpenses) { this.fundraisingExpenses = fundraisingExpenses; }
    public void setAdministrativeExpenses(Double administrativeExpenses) { this.administrativeExpenses = administrativeExpenses; }
}
