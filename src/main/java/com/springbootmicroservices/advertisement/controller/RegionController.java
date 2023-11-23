package com.springbootmicroservices.advertisement.controller;
import com.springbootmicroservices.advertisement.entity.Region;
import com.springbootmicroservices.advertisement.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin // Allow all origins for all methods in this controller
@RestController
@RequestMapping("/regions")
public class RegionController {
    @Autowired
    private RegionService regionService;

    // Create a new region
    @PostMapping
    public Region createRegion(@RequestBody Region region) {
        return regionService.createRegion(region);
    }

    // Get all regions
    @GetMapping
    public List<Region> getAllRegions() {
        return regionService.getAllRegions();
    }

    // Get region by ID
    @GetMapping("/{id}")
    public ResponseEntity<Region> getRegionById(@PathVariable Long id) {
        Region region = regionService.getRegionById(id);
        if (region != null) {
            return ResponseEntity.ok(region);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Update region by ID
    @PutMapping("/{id}")
    public ResponseEntity<Region> updateRegion(@PathVariable Long id, @RequestBody Region updatedRegion) {
        Region region = regionService.updateRegion(id, updatedRegion);
        if (region != null) {
            return ResponseEntity.ok(region);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete region by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRegion(@PathVariable Long id) {
        regionService.deleteRegion(id);
        return ResponseEntity.ok().build();
    }

    // Get all regions with pagination
    @GetMapping("/pagination")
    public Page<Region> getAllRegionsWithPagination(@RequestParam(defaultValue = "0") int page,
                                                    @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return regionService.getAllRegionsWithPagination(pageable);
    }

    // Search regions by name with pagination
    @GetMapping("/search")
    public Page<Region> searchRegionsByName(@RequestParam String regionName,
                                            @RequestParam(defaultValue = "0") int page,
                                            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return regionService.searchRegionsByName(regionName, pageable);
    }
}
