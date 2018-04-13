/*
 * ANZ Project for an Interview
 * 
 * Equity Data Signal App By Mostafa Farshchi
 * Template pack-angular:src/main/java/dto/EntityDTOService.java.e.vm
 */
package com.anz.myapp.dto;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.anz.myapp.domain.EquityPeriodical;
import com.anz.myapp.domain.EquityPeriodical_;
import com.anz.myapp.dto.support.PageRequestByExample;
import com.anz.myapp.dto.support.PageResponse;
import com.anz.myapp.repository.EquityPeriodicalRepository;

/**
 * A simple DTO Facility for EquityPeriodical.
 */
@Service
public class EquityPeriodicalDTOService {

    @Inject
    private EquityPeriodicalRepository equityPeriodicalRepository;

    @Inject
    private EntityManager em;


    public List<EquityPeriodical> findEquityPeriodicalByTimePeriodAndClosePriceDifference(
            String timePeriod, BigDecimal fromPricePercent, BigDecimal toPricePercent, Integer observedRecords) {
        System.out.println(timePeriod);
        System.out.println(fromPricePercent);
        System.out.println(toPricePercent);
        System.out.println(observedRecords);
        return em.createQuery(
                "select t from EquityPeriodical t " +
                        "where t.timePeriod = :timePeriod "+
                        "and t.priceDiffPercent between :fromPricePercent and :toPricePercent "+
                        "and t.observedRecords >= :observedRecords " +
                        "order by t.priceDiffPercent desc"
                , EquityPeriodical.class)
                .setParameter("timePeriod", timePeriod)
                .setParameter("fromPricePercent", fromPricePercent)
                .setParameter("toPricePercent", toPricePercent)
                .setParameter("observedRecords", observedRecords)
                .setMaxResults(1000)
                .getResultList();
    }

    @Transactional(readOnly = true)
    public EquityPeriodicalDTO findOne(Integer id) {
        return toDTO(equityPeriodicalRepository.findOne(id));
    }

    @Transactional(readOnly = true)
    public List<EquityPeriodicalDTO> complete(String query, int maxResults) {
        List<EquityPeriodical> results = equityPeriodicalRepository.complete(query, maxResults);
        return results.stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public PageResponse<EquityPeriodicalDTO> findAll(PageRequestByExample<EquityPeriodicalDTO> req) {
        Example<EquityPeriodical> example = null;
        EquityPeriodical equityPeriodical = toEntity(req.example);

        if (equityPeriodical != null) {
            ExampleMatcher matcher = ExampleMatcher.matching() //
                    .withMatcher(EquityPeriodical_.symbolTicker.getName(), match -> match.ignoreCase().startsWith())
                    .withMatcher(EquityPeriodical_.timePeriod.getName(), match -> match.ignoreCase().startsWith());

            example = Example.of(equityPeriodical, matcher);
        }

        Page<EquityPeriodical> page;
        if (example != null) {
            page = equityPeriodicalRepository.findAll(example, req.toPageable());
        } else {
            page = equityPeriodicalRepository.findAll(req.toPageable());
        }

        List<EquityPeriodicalDTO> content = page.getContent().stream().map(this::toDTO).collect(Collectors.toList());
        return new PageResponse<>(page.getTotalPages(), page.getTotalElements(), content);
    }

    /**
     * Save the passed dto as a new entity or update the corresponding entity if any.
     */
    @Transactional
    public EquityPeriodicalDTO save(EquityPeriodicalDTO dto) {
        if (dto == null) {
            return null;
        }

        final EquityPeriodical equityPeriodical;

        if (dto.isIdSet()) {
            EquityPeriodical equityPeriodicalTmp = equityPeriodicalRepository.findOne(dto.id);
            if (equityPeriodicalTmp != null) {
                equityPeriodical = equityPeriodicalTmp;
            } else {
                equityPeriodical = new EquityPeriodical();
                equityPeriodical.setId(dto.id);
            }
        } else {
            equityPeriodical = new EquityPeriodical();
        }

        equityPeriodical.setSymbolTicker(dto.symbolTicker);

        equityPeriodical.setTimePeriod(dto.timePeriod);

        equityPeriodical.setFirstPricingDate(dto.firstPricingDate);

        equityPeriodical.setFirstClosePrice(dto.firstClosePrice);

        equityPeriodical.setLastPricingDate(dto.lastPricingDate);

        equityPeriodical.setLastClosePrice(dto.lastClosePrice);

        equityPeriodical.setPriceDiff(dto.priceDiff);

        equityPeriodical.setPriceDiffPercent(dto.priceDiffPercent);

        equityPeriodical.setObservedRecords(dto.observedRecords);

        return toDTO(equityPeriodicalRepository.save(equityPeriodical));
    }

    /**
     * Converts the passed equityPeriodical to a DTO.
     */
    public EquityPeriodicalDTO toDTO(EquityPeriodical equityPeriodical) {
        return toDTO(equityPeriodical, 1);
    }

    /**
     * Converts the passed equityPeriodical to a DTO. The depth is used to control the
     * amount of association you want. It also prevents potential infinite serialization cycles.
     *
     * @param equityPeriodical
     * @param depth the depth of the serialization. A depth equals to 0, means no x-to-one association will be serialized.
     *              A depth equals to 1 means that xToOne associations will be serialized. 2 means, xToOne associations of
     *              xToOne associations will be serialized, etc.
     */
    public EquityPeriodicalDTO toDTO(EquityPeriodical equityPeriodical, int depth) {
        if (equityPeriodical == null) {
            return null;
        }

        EquityPeriodicalDTO dto = new EquityPeriodicalDTO();

        dto.id = equityPeriodical.getId();
        dto.symbolTicker = equityPeriodical.getSymbolTicker();
        dto.timePeriod = equityPeriodical.getTimePeriod();
        dto.firstPricingDate = equityPeriodical.getFirstPricingDate();
        dto.firstClosePrice = equityPeriodical.getFirstClosePrice();
        dto.lastPricingDate = equityPeriodical.getLastPricingDate();
        dto.lastClosePrice = equityPeriodical.getLastClosePrice();
        dto.priceDiff = equityPeriodical.getPriceDiff();
        dto.priceDiffPercent = equityPeriodical.getPriceDiffPercent();
        dto.observedRecords = equityPeriodical.getObservedRecords();
        if (depth-- > 0) {
        }

        return dto;
    }

    /**
     * Converts the passed dto to a EquityPeriodical.
     * Convenient for query by example.
     */
    public EquityPeriodical toEntity(EquityPeriodicalDTO dto) {
        return toEntity(dto, 1);
    }

    /**
     * Converts the passed dto to a EquityPeriodical.
     * Convenient for query by example.
     */
    public EquityPeriodical toEntity(EquityPeriodicalDTO dto, int depth) {
        if (dto == null) {
            return null;
        }

        EquityPeriodical equityPeriodical = new EquityPeriodical();

        equityPeriodical.setId(dto.id);
        equityPeriodical.setSymbolTicker(dto.symbolTicker);
        equityPeriodical.setTimePeriod(dto.timePeriod);
        equityPeriodical.setFirstPricingDate(dto.firstPricingDate);
        equityPeriodical.setFirstClosePrice(dto.firstClosePrice);
        equityPeriodical.setLastPricingDate(dto.lastPricingDate);
        equityPeriodical.setLastClosePrice(dto.lastClosePrice);
        equityPeriodical.setPriceDiff(dto.priceDiff);
        equityPeriodical.setPriceDiffPercent(dto.priceDiffPercent);
        equityPeriodical.setObservedRecords(dto.observedRecords);
        if (depth-- > 0) {
        }

        return equityPeriodical;
    }
}