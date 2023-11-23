package com.springbootmicroservices.advertisement.controller;

import com.springbootmicroservices.advertisement.entity.SubCounty;
import com.springbootmicroservices.advertisement.service.SubCountyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subcounties")
public class SubCountyController {
    @Autowired
    private SubCountyService subCountyService;

    // Create a new sub-county
    @PostMapping
    public SubCounty createSubCounty(@RequestBody SubCounty subCounty) {
        return subCountyService.createSubCounty(subCounty);
    }

    // Get all sub-counties
    @GetMapping
    public List<SubCounty> getAllSubCounties() {
        return subCountyService.getAllSubCounties();
    }

    // Get sub-county by ID
    @GetMapping("/{id}")
    public ResponseEntity<SubCounty> getSubCountyById(@PathVariable Long id) {
        SubCounty subCounty = subCountyService.getSubCountyById(id);
        if (subCounty != null) {
            return ResponseEntity.ok(subCounty);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Update sub-county by ID
    @PutMapping("/{id}")
    public ResponseEntity<SubCounty> updateSubCounty(@PathVariable Long id, @RequestBody SubCounty updatedSubCounty) {
        SubCounty subCounty = subCountyService.updateSubCounty(id, updatedSubCounty);
        if (subCounty != null) {
            return ResponseEntity.ok(subCounty);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete sub-county by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSubCounty(@PathVariable Long id) {
        subCountyService.deleteSubCounty(id);
        return ResponseEntity.ok().build();
    }

    // Get all sub-counties with pagination
    @GetMapping("/pagination")
    public Page<SubCounty> getAllSubCountiesWithPagination(@RequestParam(defaultValue = "0") int page,
                                                           @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return subCountyService.getAllSubCountiesWithPagination(pageable);
    }

    // Search sub-counties by name with pagination
    @GetMapping("/search")
    public Page<SubCounty> searchSubCountiesByName(@RequestParam String subCountyName,
                                                   @RequestParam(defaultValue = "0") int page,
                                                   @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return subCountyService.searchSubCountiesByName(subCountyName, pageable);
    }
}
