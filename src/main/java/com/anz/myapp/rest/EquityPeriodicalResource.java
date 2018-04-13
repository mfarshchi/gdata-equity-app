/*
 * ANZ Project for an Interview
 * 
 * Equity Data Signal App By Mostafa Farshchi
 * Template pack-angular:src/main/java/rest/EntityResource.java.e.vm
 */
package com.anz.myapp.rest;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;

import com.anz.myapp.domain.EquityPeriodical;
import com.anz.myapp.dto.EquityPeriodicalDTO;
import com.anz.myapp.dto.EquityPeriodicalDTOService;
import com.anz.myapp.dto.support.PageRequestByExample;
import com.anz.myapp.dto.support.PageResponse;
import com.anz.myapp.repository.EquityPeriodicalRepository;
import com.anz.myapp.rest.support.AutoCompleteQuery;

@RestController
@RequestMapping("/api/equityPeriodicals")
public class EquityPeriodicalResource {

    private final Logger log = LoggerFactory.getLogger(EquityPeriodicalResource.class);

    @Inject
    private EquityPeriodicalRepository equityPeriodicalRepository;
    @Inject
    private EquityPeriodicalDTOService equityPeriodicalDTOService;

    /*

        http://localhost:8080/api/equityPeriodicals/signal?timePeriod=2017-Yearly&fromPricePercent=-9999.00toPricePercent=-10.0
        http://localhost:8080/api/equityPeriodicals/signal/
        http://localhost:8080/api/equityPeriodicals/signal?timePeriod=2017-Yearly&fromPricePercent=-9999.00&toPricePercent=-10.000&observedRecords=15
    */
    @RequestMapping(value = "/signal", method = GET, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<EquityPeriodical>> equityPeriodicalSignal(
            @RequestParam(value = "timePeriod", defaultValue = "2017-Yearly") String timePeriod,
            @RequestParam(value = "fromPricePercent", defaultValue = "10.00") BigDecimal fromPricePercent,
            @RequestParam(value = "toPricePercent", defaultValue = "99999.00") BigDecimal toPricePercent,
            @RequestParam(value = "observedRecords", defaultValue = "0") Integer observedRecords
    ) {
        return new ResponseEntity<>(equityPeriodicalDTOService.findEquityPeriodicalByTimePeriodAndClosePriceDifference(timePeriod, fromPricePercent, toPricePercent, observedRecords), HttpStatus.OK);
    }


    /*

    @RequestMapping(value = "/page", method = POST, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<PageResponse<EquityPeriodicalDTO>> findAll(@RequestBody PageRequestByExample<EquityPeriodicalDTO> prbe) throws URISyntaxException {
        PageResponse<EquityPeriodicalDTO> pageResponse = equityPeriodicalDTOService.findAll(prbe);
        return new ResponseEntity<>(pageResponse, new HttpHeaders(), HttpStatus.OK);
    }
    */
    /**
     * Create a new EquityPeriodical.
     */
    @RequestMapping(value = "/", method = POST, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<EquityPeriodicalDTO> create(@RequestBody EquityPeriodicalDTO equityPeriodicalDTO) throws URISyntaxException {

        log.debug("Create EquityPeriodicalDTO : {}", equityPeriodicalDTO);

        if (equityPeriodicalDTO.isIdSet()) {
            return ResponseEntity.badRequest().header("Failure", "Cannot create EquityPeriodical with existing ID").body(null);
        }

        EquityPeriodicalDTO result = equityPeriodicalDTOService.save(equityPeriodicalDTO);

        return ResponseEntity.created(new URI("/api/equityPeriodicals/" + result.id)).body(result);
    }

    /**
    * Find by id EquityPeriodical.
    */
    @RequestMapping(value = "/{id}", method = GET, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<EquityPeriodicalDTO> findById(@PathVariable Integer id) throws URISyntaxException {

        log.debug("Find by id EquityPeriodical : {}", id);

        return Optional.ofNullable(equityPeriodicalDTOService.findOne(id)).map(equityPeriodicalDTO -> new ResponseEntity<>(equityPeriodicalDTO, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Update EquityPeriodical.
     */
    @RequestMapping(value = "/", method = PUT, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<EquityPeriodicalDTO> update(@RequestBody EquityPeriodicalDTO equityPeriodicalDTO) throws URISyntaxException {

        log.debug("Update EquityPeriodicalDTO : {}", equityPeriodicalDTO);

        if (!equityPeriodicalDTO.isIdSet()) {
            return create(equityPeriodicalDTO);
        }

        EquityPeriodicalDTO result = equityPeriodicalDTOService.save(equityPeriodicalDTO);

        return ResponseEntity.ok().body(result);
    }

    /**
     * Find a Page of EquityPeriodical using query by example.
     */
    @RequestMapping(value = "/page", method = POST, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<PageResponse<EquityPeriodicalDTO>> findAll(@RequestBody PageRequestByExample<EquityPeriodicalDTO> prbe) throws URISyntaxException {
        PageResponse<EquityPeriodicalDTO> pageResponse = equityPeriodicalDTOService.findAll(prbe);
        return new ResponseEntity<>(pageResponse, new HttpHeaders(), HttpStatus.OK);
    }

    /**
    * Auto complete support.
    */
    @RequestMapping(value = "/complete", method = POST, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<EquityPeriodicalDTO>> complete(@RequestBody AutoCompleteQuery acq) throws URISyntaxException {

        List<EquityPeriodicalDTO> results = equityPeriodicalDTOService.complete(acq.query, acq.maxResults);

        return new ResponseEntity<>(results, new HttpHeaders(), HttpStatus.OK);
    }

    /**
     * Delete by id EquityPeriodical.
     */
    @RequestMapping(value = "/{id}", method = DELETE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> delete(@PathVariable Integer id) throws URISyntaxException {

        log.debug("Delete by id EquityPeriodical : {}", id);

        try {
            equityPeriodicalRepository.delete(id);
            return ResponseEntity.ok().build();
        } catch (Exception x) {
            // todo: dig exception, most likely org.hibernate.exception.ConstraintViolationException
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}