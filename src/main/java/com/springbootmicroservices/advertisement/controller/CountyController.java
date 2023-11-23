package com.springbootmicroservices.advertisement.controller;

import com.springbootmicroservices.advertisement.entity.County;
import com.springbootmicroservices.advertisement.service.CountyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin // Allow all origins for all methods in this controller
@RestController
@RequestMapping("/counties")
public class CountyController {
    @Autowired
    private CountyService countyService;

    // Create a new county
    @PostMapping
    public County createCounty(@RequestBody County county) {
        return countyService.createCounty(county);
    }

    // Get all counties
    // Get all counties with pagination, hyperlinks, and search
    @GetMapping
    public ResponseEntity<Page<County>> getAllCounties(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String search) {

        Pageable pageable = PageRequest.of(page, size);

        Page<County> counties;
        if (search != null && !search.isEmpty()) {
            // If a search query is provided, perform a search
            counties = countyService.searchCounties(search, pageable);
        } else {
            // Otherwise, get all counties with pagination
            counties = countyService.getAllCounties(pageable);
        }

        return ResponseEntity.ok(counties);
    }

    // Get county by ID
    @GetMapping("/{id}")
    public ResponseEntity<County> getCountyById(@PathVariable Long id) {
        County county = countyService.getCountyById(id);
        if (county != null) {
            return ResponseEntity.ok(county);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Update county by ID
    @PutMapping("/{id}")
    public ResponseEntity<County> updateCounty(@PathVariable Long id, @RequestBody County updatedCounty) {
        County county = countyService.updateCounty(id, updatedCounty);
        if (county != null) {
            return ResponseEntity.ok(county);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete county by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCounty(@PathVariable Long id) {
        countyService.deleteCounty(id);
        return ResponseEntity.ok().build();
    }
}
