package com.transparency.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class DonationDTO {

    @NotNull(message = "User ID is required")
    private Long userId;

    @NotNull(message = "Charity ID is required")
    private Long charityId;

    @NotNull(message = "Amount is required")
    @Min(value = 1, message = "Donation amount must be at least 1")
    private Double amount;

    // Getters and Setters
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCharityId() {
        return charityId;
    }
    public void setCharityId(Long charityId) {
        this.charityId = charityId;
    }

    public Double getAmount() {
        return amount;
    }
    public void setAmount(Double amount) {
        this.amount = amount;
    }
}

