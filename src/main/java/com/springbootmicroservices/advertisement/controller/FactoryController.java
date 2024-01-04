package com.springbootmicroservices.advertisement.controller;

import com.springbootmicroservices.advertisement.entity.Factory;
import com.springbootmicroservices.advertisement.service.FactoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin // Allow all origins for all methods in this controller
@RestController
@RequestMapping("/factories")
public class FactoryController {
    @Autowired
    private final FactoryService factoryService;

    public FactoryController(FactoryService factoryService) {
        this.factoryService = factoryService;
    }

    // Create a new factory
    @PostMapping
    public Factory createFactory(@RequestBody Factory factory) {
        return factoryService.createFactory(factory);
    }

    // Get all factories
    @GetMapping
    public List<Factory> getAllFactories() {
        return factoryService.getAllFactories();
    }

    // Get factory by ID
    @GetMapping("/{id}")
    public ResponseEntity<Factory> getFactoryById(@PathVariable Long id) {
        Factory factory = factoryService.getFactoryById(id);
        if (factory != null) {
            return ResponseEntity.ok(factory);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Update factory by ID
    @PutMapping("/{id}")
    public ResponseEntity<Factory> updateFactory(@PathVariable Long id, @RequestBody Factory updatedFactory) {
        Factory factory = factoryService.updateFactory(id, updatedFactory);
        if (factory != null) {
            return ResponseEntity.ok(factory);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete factory by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFactory(@PathVariable Long id) {
        factoryService.deleteFactory(id);
        return ResponseEntity.ok().build();
    }

    // Get all factories with pagination
    @GetMapping("/pagination")
    public Page<Factory> getAllFactoriesWithPagination(@RequestParam(defaultValue = "0") int page,
                                                       @RequestParam(defaultValue = "10") int size) {
        Page<Factory> factoryPage = factoryService.getAllFactoriesWithPagination(PageRequest.of(page, size));
        return factoryPage;
    }

    // Search factories by factory name with pagination
    @GetMapping("/search")
    public Page<Factory> searchFactoriesByFactoryName(@RequestParam String factoryName,
                                                      @RequestParam(defaultValue = "0") int page,
                                                      @RequestParam(defaultValue = "10") int size) {
        Page<Factory> factoryPage = factoryService.searchFactoriesByFactoryName(factoryName, PageRequest.of(page, size));
        return factoryPage;
    }
}
