package com.springbootmicroservices.advertisement.controller;

import com.springbootmicroservices.advertisement.entity.FarmingType;
import com.springbootmicroservices.advertisement.service.FarmingTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin // Allow all origins for all methods in this controller
@RestController
@RequestMapping("/farmingtypes")
public class FarmingTypeController {
    @Autowired
    private FarmingTypeService farmingTypeService;



    // Create a new farming type
    @PostMapping
    public FarmingType createFarmingType(@RequestBody FarmingType farmingType) {
        return farmingTypeService.createFarmingType(farmingType);
    }

    // Get all farming types
    @GetMapping
    public List<FarmingType> getAllFarmingTypes() {
        return farmingTypeService.getAllFarmingTypes();
    }

    // Get farming type by ID
    @GetMapping("/{id}")
    public ResponseEntity<FarmingType> getFarmingTypeById(@PathVariable Long id) {
        FarmingType farmingType = farmingTypeService.getFarmingTypeById(id);
        if (farmingType != null) {
            return ResponseEntity.ok(farmingType);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Update farming type by ID
    @PutMapping("/{id}")
    public ResponseEntity<FarmingType> updateFarmingType(@PathVariable Long id, @RequestBody FarmingType updatedFarmingType) {
        FarmingType farmingType = farmingTypeService.updateFarmingType(id, updatedFarmingType);
        if (farmingType != null) {
            return ResponseEntity.ok(farmingType);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete farming type by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFarmingType(@PathVariable Long id) {
        farmingTypeService.deleteFarmingType(id);
        return ResponseEntity.ok().build();
    }

    // Get all farming types with pagination
    @GetMapping("/pagination")
    public Page<FarmingType> getAllFarmingTypesWithPagination(@RequestParam(defaultValue = "0") int page,
                                                              @RequestParam(defaultValue = "10") int size) {
        Page<FarmingType> farmingTypePage = farmingTypeService.getAllFarmingTypesWithPagination(PageRequest.of(page, size));
        return farmingTypePage;
    }

    // Search farming types by name with pagination
    @GetMapping("/search")
    public Page<FarmingType> searchFarmingTypesByName(@RequestParam String farmingTypeName,
                                                      @RequestParam(defaultValue = "0") int page,
                                                      @RequestParam(defaultValue = "10") int size) {
        Page<FarmingType> farmingTypePage = farmingTypeService.searchFarmingTypesByName(farmingTypeName, PageRequest.of(page, size));
        return farmingTypePage;
    }
}
