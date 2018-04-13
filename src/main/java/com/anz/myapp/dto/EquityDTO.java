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
 * Simple DTO for Equity.
 */
public class EquityDTO {
    public Integer id;
    public Instant pricingDate;
    public String symbolTicker;
    public String symbolExchange;
    public String currencyISO;
    public BigDecimal openPrice;
    public BigDecimal closePrice;
    public Integer volume;
    public BigDecimal dayPriceDiff;
    public BigDecimal fiveDaysPriceDiff;
    public BigDecimal monthPriceDiff;
    public BigDecimal movAvg5Days;

    @JsonIgnore
    public boolean isIdSet() {
        return id != null;
    }
}