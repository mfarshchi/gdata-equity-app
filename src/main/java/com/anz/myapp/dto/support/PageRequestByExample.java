/*
 * ANZ Project for an Interview
 * 
 * Equity Data Signal App By Mostafa Farshchi
 * Template pack-angular:src/main/java/dto/support/PageRequestByExample.java.p.vm
 */
package com.anz.myapp.dto.support;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Pageable;

public class PageRequestByExample<DTO> {
    public DTO example;
    public LazyLoadEvent lazyLoadEvent;

    public Pageable toPageable() {
        return lazyLoadEvent != null ? lazyLoadEvent.toPageable() : null;
    }
}