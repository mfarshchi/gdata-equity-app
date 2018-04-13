/*
 * ANZ Project for an Interview
 * 
 * Equity Data Signal App By Mostafa Farshchi
 * Template pack-angular:src/main/java/domain/Entity.java.e.vm
 */
package com.anz.myapp.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.logging.Logger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

@Entity
@Table(name = "EquityPeriodical", uniqueConstraints = {
        @UniqueConstraint(name = "UC_CompanyEquityPeriodical", columnNames = { "SymbolTicker", "TimePeriod" }) })
public class EquityPeriodical implements Identifiable<Integer>, Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = Logger.getLogger(EquityPeriodical.class.getName());

    // Raw attributes
    private Integer id;
    private String symbolTicker;
    private String timePeriod;
    private Instant firstPricingDate;
    private BigDecimal firstClosePrice;
    private Instant lastPricingDate;
    private BigDecimal lastClosePrice;
    private BigDecimal priceDiff;
    private BigDecimal priceDiffPercent;
    private Integer observedRecords;

    @Override
    public String entityClassName() {
        return EquityPeriodical.class.getSimpleName();
    }

    // -- [id] ------------------------

    @Override
    @Column(name = "CompanyEquityPeriodicalID", precision = 10)
    @GeneratedValue(strategy = IDENTITY)
    @Id
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public EquityPeriodical id(Integer id) {
        setId(id);
        return this;
    }

    @Override
    @Transient
    public boolean isIdSet() {
        return id != null;
    }
    // -- [symbolTicker] ------------------------

    @NotEmpty
    @Size(max = 10)
    @Column(name = "SymbolTicker", nullable = false, length = 10)
    public String getSymbolTicker() {
        return symbolTicker;
    }

    public void setSymbolTicker(String symbolTicker) {
        this.symbolTicker = symbolTicker;
    }

    public EquityPeriodical symbolTicker(String symbolTicker) {
        setSymbolTicker(symbolTicker);
        return this;
    }
    // -- [timePeriod] ------------------------

    @NotEmpty
    @Size(max = 30)
    @Column(name = "TimePeriod", nullable = false, length = 30)
    public String getTimePeriod() {
        return timePeriod;
    }

    public void setTimePeriod(String timePeriod) {
        this.timePeriod = timePeriod;
    }

    public EquityPeriodical timePeriod(String timePeriod) {
        setTimePeriod(timePeriod);
        return this;
    }
    // -- [firstPricingDate] ------------------------

    @NotNull
    @Column(name = "FirstPricingDate", nullable = false, length = 19)
    public Instant getFirstPricingDate() {
        return firstPricingDate;
    }

    public void setFirstPricingDate(Instant firstPricingDate) {
        this.firstPricingDate = firstPricingDate;
    }

    public EquityPeriodical firstPricingDate(Instant firstPricingDate) {
        setFirstPricingDate(firstPricingDate);
        return this;
    }
    // -- [firstClosePrice] ------------------------

    @Digits(integer = 6, fraction = 6)
    @Column(name = "FirstClosePrice", precision = 12, scale = 6)
    public BigDecimal getFirstClosePrice() {
        return firstClosePrice;
    }

    public void setFirstClosePrice(BigDecimal firstClosePrice) {
        this.firstClosePrice = firstClosePrice;
    }

    public EquityPeriodical firstClosePrice(BigDecimal firstClosePrice) {
        setFirstClosePrice(firstClosePrice);
        return this;
    }
    // -- [lastPricingDate] ------------------------

    @NotNull
    @Column(name = "LastPricingDate", nullable = false, length = 19)
    public Instant getLastPricingDate() {
        return lastPricingDate;
    }

    public void setLastPricingDate(Instant lastPricingDate) {
        this.lastPricingDate = lastPricingDate;
    }

    public EquityPeriodical lastPricingDate(Instant lastPricingDate) {
        setLastPricingDate(lastPricingDate);
        return this;
    }
    // -- [lastClosePrice] ------------------------

    @Digits(integer = 6, fraction = 6)
    @Column(name = "LastClosePrice", precision = 12, scale = 6)
    public BigDecimal getLastClosePrice() {
        return lastClosePrice;
    }

    public void setLastClosePrice(BigDecimal lastClosePrice) {
        this.lastClosePrice = lastClosePrice;
    }

    public EquityPeriodical lastClosePrice(BigDecimal lastClosePrice) {
        setLastClosePrice(lastClosePrice);
        return this;
    }
    // -- [priceDiff] ------------------------

    @Digits(integer = 6, fraction = 6)
    @Column(name = "PriceDiff", precision = 12, scale = 6)
    public BigDecimal getPriceDiff() {
        return priceDiff;
    }

    public void setPriceDiff(BigDecimal priceDiff) {
        this.priceDiff = priceDiff;
    }

    public EquityPeriodical priceDiff(BigDecimal priceDiff) {
        setPriceDiff(priceDiff);
        return this;
    }
    // -- [priceDiffPercent] ------------------------

    @Digits(integer = 6, fraction = 6)
    @Column(name = "PriceDiffPercent", precision = 12, scale = 6)
    public BigDecimal getPriceDiffPercent() {
        return priceDiffPercent;
    }

    public void setPriceDiffPercent(BigDecimal priceDiffPercent) {
        this.priceDiffPercent = priceDiffPercent;
    }

    public EquityPeriodical priceDiffPercent(BigDecimal priceDiffPercent) {
        setPriceDiffPercent(priceDiffPercent);
        return this;
    }
    // -- [observedRecords] ------------------------

    @Digits(integer = 5, fraction = 0)
    @Column(name = "ObservedRecords", precision = 5)
    public Integer getObservedRecords() {
        return observedRecords;
    }

    public void setObservedRecords(Integer observedRecords) {
        this.observedRecords = observedRecords;
    }

    public EquityPeriodical observedRecords(Integer observedRecords) {
        setObservedRecords(observedRecords);
        return this;
    }

    /**
     * Apply the default values.
     */
    public EquityPeriodical withDefaults() {
        setObservedRecords(0);
        return this;
    }

    /**
     * Equals implementation using a business key.
     */
    @Override
    public boolean equals(Object other) {
        return this == other || (other instanceof EquityPeriodical && hashCode() == other.hashCode());
    }

    private volatile int previousHashCode = 0;

    @Override
    public int hashCode() {
        int hashCode = Objects.hashCode(getSymbolTicker(), //
                getTimePeriod());

        if (previousHashCode != 0 && previousHashCode != hashCode) {
            log.warning("DEVELOPER: hashCode has changed!." //
                    + "If you encounter this message you should take the time to carefuly review equals/hashCode for: " //
                    + getClass().getCanonicalName());
        }

        previousHashCode = hashCode;
        return hashCode;
    }

    /**
     * Construct a readable string representation for this EquityPeriodical instance.
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this) //
                .add("id", getId()) //
                .add("symbolTicker", getSymbolTicker()) //
                .add("timePeriod", getTimePeriod()) //
                .add("firstPricingDate", getFirstPricingDate()) //
                .add("firstClosePrice", getFirstClosePrice()) //
                .add("lastPricingDate", getLastPricingDate()) //
                .add("lastClosePrice", getLastClosePrice()) //
                .add("priceDiff", getPriceDiff()) //
                .add("priceDiffPercent", getPriceDiffPercent()) //
                .add("observedRecords", getObservedRecords()) //
                .toString();
    }
}