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

@StaticMetamodel(EquityPeriodical.class)
public abstract class EquityPeriodical_ {

    // Raw attributes
    public static volatile SingularAttribute<EquityPeriodical, Integer> id;
    public static volatile SingularAttribute<EquityPeriodical, String> symbolTicker;
    public static volatile SingularAttribute<EquityPeriodical, String> timePeriod;
    public static volatile SingularAttribute<EquityPeriodical, Instant> firstPricingDate;
    public static volatile SingularAttribute<EquityPeriodical, BigDecimal> firstClosePrice;
    public static volatile SingularAttribute<EquityPeriodical, Instant> lastPricingDate;
    public static volatile SingularAttribute<EquityPeriodical, BigDecimal> lastClosePrice;
    public static volatile SingularAttribute<EquityPeriodical, BigDecimal> priceDiff;
    public static volatile SingularAttribute<EquityPeriodical, BigDecimal> priceDiffPercent;
    public static volatile SingularAttribute<EquityPeriodical, Integer> observedRecords;
}