/*
 * ANZ Project for an Interview
 * 
 * Equity Data Signal App By Mostafa Farshchi
 * Template pack-angular:src/main/java/dto/support/PageResponse.java.p.vm
 */
package com.anz.myapp.dto.support;

import java.util.List;

public class PageResponse<T> {

    public final int totalPages;
    public final long totalElements;
    public final List<T> content;

    public PageResponse(int totalPages, long totalElements, List<T> content) {
        this.totalPages = totalPages;
        this.totalElements = totalElements;
        this.content = content;
    }
}
