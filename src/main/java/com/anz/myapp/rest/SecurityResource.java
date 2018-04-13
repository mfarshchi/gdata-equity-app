/*
 * ANZ Project for an Interview
 * 
 * Equity Data Signal App By Mostafa Farshchi
 * Template pack-angular:src/main/java/rest/SecurityResource.java.p.vm
 */
package com.anz.myapp.rest;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.*;

import com.anz.myapp.security.UserContext;

@RestController
@RequestMapping("/api")
public class SecurityResource {

    @RequestMapping(value = "/authenticated", method = GET, produces = APPLICATION_JSON_VALUE)
    public boolean isAuthenticated() {
        return UserContext.getId() != null;
    }
}