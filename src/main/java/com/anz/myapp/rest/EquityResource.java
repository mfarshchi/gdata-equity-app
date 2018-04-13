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

import java.net.URI;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
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

import com.anz.myapp.domain.Equity;
import com.anz.myapp.dto.EquityDTO;
import com.anz.myapp.dto.EquityDTOService;
import com.anz.myapp.dto.support.PageRequestByExample;
import com.anz.myapp.dto.support.PageResponse;
import com.anz.myapp.repository.EquityRepository;
import com.anz.myapp.rest.support.AutoCompleteQuery;

@RestController
@RequestMapping("/api/equities")
public class EquityResource {

    private final Logger log = LoggerFactory.getLogger(EquityResource.class);

    @Inject
    private EquityRepository equityRepository;
    @Inject
    private EquityDTOService equityDTOService;

    @RequestMapping(value = "/check", method = GET, produces = APPLICATION_JSON_VALUE)
    public boolean isAuthenticated() {
        return true;
    }


    @RequestMapping(value = "/equityQuery", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<Equity>> equityQuery(
            @RequestParam(value = "symbolTicker", defaultValue = "VLC") String symbolTicker, //default values are only for facilitating testing
            @RequestParam(value = "fromDate", defaultValue = "2017-01-01 12:00:00") String fromDate,
            @RequestParam(value = "toDate", defaultValue = "2017-12-01 12:00:00") String toDate
            ) {
        return new ResponseEntity<>(equityDTOService.findEquityBySymbolAndDateRange(symbolTicker, toInstant(fromDate), toInstant(toDate)), HttpStatus.OK);
    }

    @RequestMapping(value = "/equityChart", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<String> equityDailyPriceRawChartData(
            @RequestParam(value = "symbolTicker", defaultValue = "VLC") String symbolTicker, //default values are only for facilitating testing
            @RequestParam(value = "fromDate", defaultValue = "2017-01-01 12:00:00") String fromDate,
            @RequestParam(value = "toDate", defaultValue = "2017-12-01 12:00:00") String toDate
    ) {
        String returnData = chartDailyPrice(new ArrayList<Equity>(equityDTOService.findEquityBySymbolAndDateRange(symbolTicker, toInstant(fromDate), toInstant(toDate))));
        return new ResponseEntity<String> ((returnData), HttpStatus.OK);
    }

    /*
        To convert data for Highcharts
     */
    public String chartDailyPrice (ArrayList<Equity> equities){

        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append(" [");
        for(int i=0; i< equities.size(); i++){
            strBuilder.append("[");
            strBuilder.append(equities.get(i).getPricingDate().toEpochMilli());
            strBuilder.append(",");
            strBuilder.append(equities.get(i).getClosePrice());
            strBuilder.append("]");
            if (i < equities.size() -1) strBuilder.append(",");
        }
        strBuilder.deleteCharAt(strBuilder.lastIndexOf(strBuilder.toString()));
        strBuilder.append("]");
        return strBuilder.toString();
    }

    /*
        String to Date/Instant conversion
     */
    private static Instant toInstant(String dateString) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date inputDate = null;
        try {
            inputDate = dateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return inputDate.toInstant();
    }

    /**
     * Create a new Equity.
     */
    @RequestMapping(value = "/", method = POST, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<EquityDTO> create(@RequestBody EquityDTO equityDTO) throws URISyntaxException {

        log.debug("Create EquityDTO : {}", equityDTO);

        if (equityDTO.isIdSet()) {
            return ResponseEntity.badRequest().header("Failure", "Cannot create Equity with existing ID").body(null);
        }

        EquityDTO result = equityDTOService.save(equityDTO);

        return ResponseEntity.created(new URI("/api/equities/" + result.id)).body(result);
    }

    /**
    * Find by id Equity.
    */
    @RequestMapping(value = "/{id}", method = GET, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<EquityDTO> findById(@PathVariable Integer id) throws URISyntaxException {

        log.debug("Find by id Equity : {}", id);

        return Optional.ofNullable(equityDTOService.findOne(id)).map(equityDTO -> new ResponseEntity<>(equityDTO, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Update Equity.
     */
    @RequestMapping(value = "/", method = PUT, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<EquityDTO> update(@RequestBody EquityDTO equityDTO) throws URISyntaxException {

        log.debug("Update EquityDTO : {}", equityDTO);

        if (!equityDTO.isIdSet()) {
            return create(equityDTO);
        }

        EquityDTO result = equityDTOService.save(equityDTO);

        return ResponseEntity.ok().body(result);
    }

    /**
     * Find a Page of Equity using query by example.
     */
    @RequestMapping(value = "/page", method = POST, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<PageResponse<EquityDTO>> findAll(@RequestBody PageRequestByExample<EquityDTO> prbe) throws URISyntaxException {
        PageResponse<EquityDTO> pageResponse = equityDTOService.findAll(prbe);
        return new ResponseEntity<>(pageResponse, new HttpHeaders(), HttpStatus.OK);
    }

    /**
    * Auto complete support.
    */
    @RequestMapping(value = "/complete", method = POST, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<EquityDTO>> complete(@RequestBody AutoCompleteQuery acq) throws URISyntaxException {

        List<EquityDTO> results = equityDTOService.complete(acq.query, acq.maxResults);

        return new ResponseEntity<>(results, new HttpHeaders(), HttpStatus.OK);
    }

    /**
     * Delete by id Equity.
     */
    @RequestMapping(value = "/{id}", method = DELETE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> delete(@PathVariable Integer id) throws URISyntaxException {

        log.debug("Delete by id Equity : {}", id);

        try {
            equityRepository.delete(id);
            return ResponseEntity.ok().build();
        } catch (Exception x) {
            // todo: dig exception, most likely org.hibernate.exception.ConstraintViolationException
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}