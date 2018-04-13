/*
 * ANZ Project for an Interview
 * 
 * Equity Data Signal App By Mostafa Farshchi
 * Template pack-angular:src/main/java/repository/EntityRepository.java.e.vm
 */
package com.anz.myapp.repository;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.*;

import com.anz.myapp.domain.EquityPeriodical;
import com.anz.myapp.domain.EquityPeriodical_;

public interface EquityPeriodicalRepository extends JpaRepository<EquityPeriodical, Integer> {

    default List<EquityPeriodical> complete(String query, int maxResults) {
        EquityPeriodical probe = new EquityPeriodical();
        probe.setSymbolTicker(query);

        ExampleMatcher matcher = ExampleMatcher.matching() //
                .withMatcher(EquityPeriodical_.symbolTicker.getName(), match -> match.ignoreCase().startsWith());

        Page<EquityPeriodical> page = findAll(Example.of(probe, matcher), new PageRequest(0, maxResults));
        return page.getContent();
    }
}