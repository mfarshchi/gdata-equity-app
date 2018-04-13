/*
 * ANZ Project for an Interview
 * 
 * Equity Data Signal App By Mostafa Farshchi
 * Template pack-angular:src/main/java/dto/EntityDTOService.java.e.vm
 */
package com.anz.myapp.dto;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.anz.myapp.domain.Equity;
import com.anz.myapp.domain.Equity_;
import com.anz.myapp.dto.support.PageRequestByExample;
import com.anz.myapp.dto.support.PageResponse;
import com.anz.myapp.repository.EquityRepository;

/**
 * A simple DTO Facility for Equity.
 */
@Service
public class EquityDTOService {

    @Inject
    private EquityRepository equityRepository;

    @PersistenceContext
    private EntityManager em;

    public List<Equity> findEquityBySymbolAndDateRange(String symbolTicker, Instant fromDate, Instant toDate) {
        System.out.println(symbolTicker);
        System.out.println(fromDate);
        System.out.println(toDate);
        return em.createQuery(
                "select t from Equity t " +
                        "where t.symbolTicker = :symbolTicker "+
                        "and t.pricingDate between :fromDate and :toDate order by t.pricingDate"
                , Equity.class)
                .setParameter("symbolTicker", symbolTicker)
                .setParameter("fromDate", fromDate)
                .setParameter("toDate", toDate)
                .setMaxResults(1000)
                .getResultList();
    }


    @Transactional(readOnly = true)
    public EquityDTO findOne(Integer id) {
        return toDTO(equityRepository.findOne(id));
    }

    @Transactional(readOnly = true)
    public List<EquityDTO> complete(String query, int maxResults) {
        List<Equity> results = equityRepository.complete(query, maxResults);
        return results.stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public PageResponse<EquityDTO> findAll(PageRequestByExample<EquityDTO> req) {
        Example<Equity> example = null;
        Equity equity = toEntity(req.example);

        if (equity != null) {
            ExampleMatcher matcher = ExampleMatcher.matching() //
                    .withMatcher(Equity_.symbolTicker.getName(), match -> match.ignoreCase().startsWith())
                    .withMatcher(Equity_.symbolExchange.getName(), match -> match.ignoreCase().startsWith())
                    .withMatcher(Equity_.currencyISO.getName(), match -> match.ignoreCase().startsWith());

            example = Example.of(equity, matcher);
        }

        Page<Equity> page;
        if (example != null) {
            page = equityRepository.findAll(example, req.toPageable());
        } else {
            page = equityRepository.findAll(req.toPageable());
        }

        List<EquityDTO> content = page.getContent().stream().map(this::toDTO).collect(Collectors.toList());
        return new PageResponse<>(page.getTotalPages(), page.getTotalElements(), content);
    }

    /**
     * Save the passed dto as a new entity or update the corresponding entity if any.
     */
    @Transactional
    public EquityDTO save(EquityDTO dto) {
        if (dto == null) {
            return null;
        }

        final Equity equity;

        if (dto.isIdSet()) {
            Equity equityTmp = equityRepository.findOne(dto.id);
            if (equityTmp != null) {
                equity = equityTmp;
            } else {
                equity = new Equity();
                equity.setId(dto.id);
            }
        } else {
            equity = new Equity();
        }

        equity.setPricingDate(dto.pricingDate);

        equity.setSymbolTicker(dto.symbolTicker);

        equity.setSymbolExchange(dto.symbolExchange);

        equity.setCurrencyISO(dto.currencyISO);

        equity.setOpenPrice(dto.openPrice);

        equity.setClosePrice(dto.closePrice);

        equity.setVolume(dto.volume);

        equity.setDayPriceDiff(dto.dayPriceDiff);

        equity.setFiveDaysPriceDiff(dto.fiveDaysPriceDiff);

        equity.setMonthPriceDiff(dto.monthPriceDiff);

        equity.setMovAvg5Days(dto.movAvg5Days);

        return toDTO(equityRepository.save(equity));
    }

    /**
     * Converts the passed equity to a DTO.
     */
    public EquityDTO toDTO(Equity equity) {
        return toDTO(equity, 1);
    }

    /**
     * Converts the passed equity to a DTO. The depth is used to control the
     * amount of association you want. It also prevents potential infinite serialization cycles.
     *
     * @param equity
     * @param depth the depth of the serialization. A depth equals to 0, means no x-to-one association will be serialized.
     *              A depth equals to 1 means that xToOne associations will be serialized. 2 means, xToOne associations of
     *              xToOne associations will be serialized, etc.
     */
    public EquityDTO toDTO(Equity equity, int depth) {
        if (equity == null) {
            return null;
        }

        EquityDTO dto = new EquityDTO();

        dto.id = equity.getId();
        dto.pricingDate = equity.getPricingDate();
        dto.symbolTicker = equity.getSymbolTicker();
        dto.symbolExchange = equity.getSymbolExchange();
        dto.currencyISO = equity.getCurrencyISO();
        dto.openPrice = equity.getOpenPrice();
        dto.closePrice = equity.getClosePrice();
        dto.volume = equity.getVolume();
        dto.dayPriceDiff = equity.getDayPriceDiff();
        dto.fiveDaysPriceDiff = equity.getFiveDaysPriceDiff();
        dto.monthPriceDiff = equity.getMonthPriceDiff();
        dto.movAvg5Days = equity.getMovAvg5Days();
        if (depth-- > 0) {
        }

        return dto;
    }

    /**
     * Converts the passed dto to a Equity.
     * Convenient for query by example.
     */
    public Equity toEntity(EquityDTO dto) {
        return toEntity(dto, 1);
    }

    /**
     * Converts the passed dto to a Equity.
     * Convenient for query by example.
     */
    public Equity toEntity(EquityDTO dto, int depth) {
        if (dto == null) {
            return null;
        }

        Equity equity = new Equity();

        equity.setId(dto.id);
        equity.setPricingDate(dto.pricingDate);
        equity.setSymbolTicker(dto.symbolTicker);
        equity.setSymbolExchange(dto.symbolExchange);
        equity.setCurrencyISO(dto.currencyISO);
        equity.setOpenPrice(dto.openPrice);
        equity.setClosePrice(dto.closePrice);
        equity.setVolume(dto.volume);
        equity.setDayPriceDiff(dto.dayPriceDiff);
        equity.setFiveDaysPriceDiff(dto.fiveDaysPriceDiff);
        equity.setMonthPriceDiff(dto.monthPriceDiff);
        equity.setMovAvg5Days(dto.movAvg5Days);
        if (depth-- > 0) {
        }

        return equity;
    }
}