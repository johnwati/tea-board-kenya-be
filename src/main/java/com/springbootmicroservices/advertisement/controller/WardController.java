package com.springbootmicroservices.advertisement.controller;

import com.springbootmicroservices.advertisement.entity.Ward;
import com.springbootmicroservices.advertisement.service.WardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin // Allow all origins for all methods in this controller
@RestController
@RequestMapping("/wards")
public class WardController {
    @Autowired
    private WardService wardService;

    // Create a new ward
    @PostMapping
    public Ward createWard(@RequestBody Ward ward) {
        return wardService.createWard(ward);
    }

    // Get all wards
    @GetMapping
    public List<Ward> getAllWards() {
        return wardService.getAllWards();
    }

    // Get ward by ID
    @GetMapping("/{id}")
    public ResponseEntity<Ward> getWardById(@PathVariable Long id) {
        Ward ward = wardService.getWardById(id);
        if (ward != null) {
            return ResponseEntity.ok(ward);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Update ward by ID
    @PutMapping("/{id}")
    public ResponseEntity<Ward> updateWard(@PathVariable Long id, @RequestBody Ward updatedWard) {
        Ward ward = wardService.updateWard(id, updatedWard);
        if (ward != null) {
            return ResponseEntity.ok(ward);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete ward by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWard(@PathVariable Long id) {
        wardService.deleteWard(id);
        return ResponseEntity.ok().build();
    }

    // Get all wards with pagination
    @GetMapping("/pagination")
    public Page<Ward> getAllWardsWithPagination(@RequestParam(defaultValue = "0") int page,
                                                @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return wardService.getAllWardsWithPagination(pageable);
    }

    // Search wards by name with pagination
    @GetMapping("/search")
    public Page<Ward> searchWardsByName(@RequestParam String wardName,
                                        @RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return wardService.searchWardsByName(wardName, pageable);
    }
}

