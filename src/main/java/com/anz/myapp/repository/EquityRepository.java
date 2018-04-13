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

import com.anz.myapp.domain.Equity;
import com.anz.myapp.domain.Equity_;

public interface EquityRepository extends JpaRepository<Equity, Integer> {

    default List<Equity> complete(String query, int maxResults) {
        Equity probe = new Equity();
        probe.setSymbolTicker(query);

        ExampleMatcher matcher = ExampleMatcher.matching() //
                .withMatcher(Equity_.symbolTicker.getName(), match -> match.ignoreCase().startsWith());

        Page<Equity> page = findAll(Example.of(probe, matcher), new PageRequest(0, maxResults));
        return page.getContent();
    }
}