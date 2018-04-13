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

import com.anz.myapp.validation.FixedLength;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

@Entity
@Table(name = "Equity", uniqueConstraints = { @UniqueConstraint(name = "UC_COmpanyEquity", columnNames = { "PricingDate", "SymbolTicker", "SymbolExchange" }) })
public class Equity implements Identifiable<Integer>, Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = Logger.getLogger(Equity.class.getName());

    // Raw attributes
    private Integer id;
    private Instant pricingDate;
    private String symbolTicker;
    private String symbolExchange;
    private String currencyISO;
    private BigDecimal openPrice;
    private BigDecimal closePrice;
    private Integer volume;
    private BigDecimal dayPriceDiff;
    private BigDecimal fiveDaysPriceDiff;
    private BigDecimal monthPriceDiff;
    private BigDecimal movAvg5Days;

    @Override
    public String entityClassName() {
        return Equity.class.getSimpleName();
    }

    // -- [id] ------------------------

    @Override
    @Column(name = "CompanyEquityID", precision = 10)
    @GeneratedValue(strategy = IDENTITY)
    @Id
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Equity id(Integer id) {
        setId(id);
        return this;
    }

    @Override
    @Transient
    public boolean isIdSet() {
        return id != null;
    }
    // -- [pricingDate] ------------------------

    @NotNull
    @Column(name = "PricingDate", nullable = false, length = 19)
    public Instant getPricingDate() {
        return pricingDate;
    }

    public void setPricingDate(Instant pricingDate) {
        this.pricingDate = pricingDate;
    }

    public Equity pricingDate(Instant pricingDate) {
        setPricingDate(pricingDate);
        return this;
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

    public Equity symbolTicker(String symbolTicker) {
        setSymbolTicker(symbolTicker);
        return this;
    }
    // -- [symbolExchange] ------------------------

    @NotEmpty
    @Size(max = 10)
    @Column(name = "SymbolExchange", nullable = false, length = 10)
    public String getSymbolExchange() {
        return symbolExchange;
    }

    public void setSymbolExchange(String symbolExchange) {
        this.symbolExchange = symbolExchange;
    }

    public Equity symbolExchange(String symbolExchange) {
        setSymbolExchange(symbolExchange);
        return this;
    }
    // -- [currencyISO] ------------------------

    @FixedLength(length = 3)
    @Column(name = "CurrencyISO", length = 3)
    public String getCurrencyISO() {
        return currencyISO;
    }

    public void setCurrencyISO(String currencyISO) {
        this.currencyISO = currencyISO;
    }

    public Equity currencyISO(String currencyISO) {
        setCurrencyISO(currencyISO);
        return this;
    }
    // -- [openPrice] ------------------------

    @Digits(integer = 6, fraction = 6)
    @Column(name = "OpenPrice", precision = 12, scale = 6)
    public BigDecimal getOpenPrice() {
        return openPrice;
    }

    public void setOpenPrice(BigDecimal openPrice) {
        this.openPrice = openPrice;
    }

    public Equity openPrice(BigDecimal openPrice) {
        setOpenPrice(openPrice);
        return this;
    }
    // -- [closePrice] ------------------------

    @Digits(integer = 6, fraction = 6)
    @Column(name = "ClosePrice", precision = 12, scale = 6)
    public BigDecimal getClosePrice() {
        return closePrice;
    }

    public void setClosePrice(BigDecimal closePrice) {
        this.closePrice = closePrice;
    }

    public Equity closePrice(BigDecimal closePrice) {
        setClosePrice(closePrice);
        return this;
    }
    // -- [volume] ------------------------

    @Digits(integer = 10, fraction = 0)
    @NotNull
    @Column(name = "Volume", nullable = false, precision = 10)
    public Integer getVolume() {
        return volume;
    }

    public void setVolume(Integer volume) {
        this.volume = volume;
    }

    public Equity volume(Integer volume) {
        setVolume(volume);
        return this;
    }
    // -- [dayPriceDiff] ------------------------

    @Digits(integer = 6, fraction = 6)
    @Column(name = "DayPriceDiff", precision = 12, scale = 6)
    public BigDecimal getDayPriceDiff() {
        return dayPriceDiff;
    }

    public void setDayPriceDiff(BigDecimal dayPriceDiff) {
        this.dayPriceDiff = dayPriceDiff;
    }

    public Equity dayPriceDiff(BigDecimal dayPriceDiff) {
        setDayPriceDiff(dayPriceDiff);
        return this;
    }
    // -- [fiveDaysPriceDiff] ------------------------

    @Digits(integer = 6, fraction = 6)
    @Column(name = "FiveDaysPriceDiff", precision = 12, scale = 6)
    public BigDecimal getFiveDaysPriceDiff() {
        return fiveDaysPriceDiff;
    }

    public void setFiveDaysPriceDiff(BigDecimal fiveDaysPriceDiff) {
        this.fiveDaysPriceDiff = fiveDaysPriceDiff;
    }

    public Equity fiveDaysPriceDiff(BigDecimal fiveDaysPriceDiff) {
        setFiveDaysPriceDiff(fiveDaysPriceDiff);
        return this;
    }
    // -- [monthPriceDiff] ------------------------

    @Digits(integer = 6, fraction = 6)
    @Column(name = "MonthPriceDiff", precision = 12, scale = 6)
    public BigDecimal getMonthPriceDiff() {
        return monthPriceDiff;
    }

    public void setMonthPriceDiff(BigDecimal monthPriceDiff) {
        this.monthPriceDiff = monthPriceDiff;
    }

    public Equity monthPriceDiff(BigDecimal monthPriceDiff) {
        setMonthPriceDiff(monthPriceDiff);
        return this;
    }
    // -- [movAvg5Days] ------------------------

    @Digits(integer = 6, fraction = 6)
    @Column(name = "MovAvg5Days", precision = 12, scale = 6)
    public BigDecimal getMovAvg5Days() {
        return movAvg5Days;
    }

    public void setMovAvg5Days(BigDecimal movAvg5Days) {
        this.movAvg5Days = movAvg5Days;
    }

    public Equity movAvg5Days(BigDecimal movAvg5Days) {
        setMovAvg5Days(movAvg5Days);
        return this;
    }

    /**
     * Apply the default values.
     */
    public Equity withDefaults() {
        return this;
    }

    /**
     * Equals implementation using a business key.
     */
    @Override
    public boolean equals(Object other) {
        return this == other || (other instanceof Equity && hashCode() == other.hashCode());
    }

    private IdentifiableHashBuilder identifiableHashBuilder = new IdentifiableHashBuilder();

    @Override
    public int hashCode() {
        return identifiableHashBuilder.hash(log, this);
    }

    /**
     * Construct a readable string representation for this Equity instance.
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this) //
                .add("id", getId()) //
                .add("pricingDate", getPricingDate()) //
                .add("symbolTicker", getSymbolTicker()) //
                .add("symbolExchange", getSymbolExchange()) //
                .add("currencyISO", getCurrencyISO()) //
                .add("openPrice", getOpenPrice()) //
                .add("closePrice", getClosePrice()) //
                .add("volume", getVolume()) //
                .add("dayPriceDiff", getDayPriceDiff()) //
                .add("fiveDaysPriceDiff", getFiveDaysPriceDiff()) //
                .add("monthPriceDiff", getMonthPriceDiff()) //
                .add("movAvg5Days", getMovAvg5Days()) //
                .toString();
    }
}