/*
 * ANZ Project for an Interview
 * 
 * Equity Data Signal App By Mostafa Farshchi
 * Template pack-angular:src/main/java/dto/EntityDTO.java.e.vm
 */
package com.anz.myapp.dto;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Simple DTO for User.
 */
public class UserDTO {
    public Integer id;
    public String login;
    public String password;
    public String email;
    public Boolean isEnabled;
    public String civility;
    public String countryCode;
    public String firstName;
    public String lastName;
    public Instant creationDate;
    public String creationAuthor;
    public Instant lastModificationDate;
    public String lastModificationAuthor;
    public Integer version;

    @JsonIgnore
    public boolean isIdSet() {
        return id != null;
    }
}