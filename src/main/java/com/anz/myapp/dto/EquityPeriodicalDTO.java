/*
 * ANZ Project for an Interview
 * 
 * Equity Data Signal App By Mostafa Farshchi
 * Template pack-angular:src/main/java/dto/EntityDTO.java.e.vm
 */
package com.anz.myapp.dto;

import java.math.BigDecimal;
import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Simple DTO for EquityPeriodical.
 */
public class EquityPeriodicalDTO {
    public Integer id;
    public String symbolTicker;
    public String timePeriod;
    public Instant firstPricingDate;
    public BigDecimal firstClosePrice;
    public Instant lastPricingDate;
    public BigDecimal lastClosePrice;
    public BigDecimal priceDiff;
    public BigDecimal priceDiffPercent;
    public Integer observedRecords;

    @JsonIgnore
    public boolean isIdSet() {
        return id != null;
    }
}