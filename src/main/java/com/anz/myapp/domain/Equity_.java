/*
 * ANZ Project for an Interview
 * 
 * Equity Data Signal App By Mostafa Farshchi
 * Template pack-angular:src/main/java/domain/EntityMeta_.java.e.vm
 */
package com.anz.myapp.domain;

import java.math.BigDecimal;
import java.time.Instant;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Equity.class)
public abstract class Equity_ {

    // Raw attributes
    public static volatile SingularAttribute<Equity, Integer> id;
    public static volatile SingularAttribute<Equity, Instant> pricingDate;
    public static volatile SingularAttribute<Equity, String> symbolTicker;
    public static volatile SingularAttribute<Equity, String> symbolExchange;
    public static volatile SingularAttribute<Equity, String> currencyISO;
    public static volatile SingularAttribute<Equity, BigDecimal> openPrice;
    public static volatile SingularAttribute<Equity, BigDecimal> closePrice;
    public static volatile SingularAttribute<Equity, Integer> volume;
    public static volatile SingularAttribute<Equity, BigDecimal> dayPriceDiff;
    public static volatile SingularAttribute<Equity, BigDecimal> fiveDaysPriceDiff;
    public static volatile SingularAttribute<Equity, BigDecimal> monthPriceDiff;
    public static volatile SingularAttribute<Equity, BigDecimal> movAvg5Days;
}