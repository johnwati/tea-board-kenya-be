package com.springbootmicroservices.advertisement.controller;

import com.springbootmicroservices.advertisement.entity.TeaVariety;
import com.springbootmicroservices.advertisement.service.TeaVarietyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin // Allow all origins for all methods in this controller
@RestController
@RequestMapping("/teavarieties")
public class TeaVarietyController {
    @Autowired
    private TeaVarietyService teaVarietyService;



    // Create a new tea variety
    @PostMapping
    public TeaVariety createTeaVariety(@RequestBody TeaVariety teaVariety) {
        return teaVarietyService.createTeaVariety(teaVariety);
    }

    // Get all tea varieties
    @GetMapping
    public List<TeaVariety> getAllTeaVarieties() {
        return teaVarietyService.getAllTeaVarieties();
    }

    // Get tea variety by ID
    @GetMapping("/{id}")
    public ResponseEntity<TeaVariety> getTeaVarietyById(@PathVariable Long id) {
        TeaVariety teaVariety = teaVarietyService.getTeaVarietyById(id);
        if (teaVariety != null) {
            return ResponseEntity.ok(teaVariety);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Update tea variety by ID
    @PutMapping("/{id}")
    public ResponseEntity<TeaVariety> updateTeaVariety(@PathVariable Long id, @RequestBody TeaVariety updatedTeaVariety) {
        TeaVariety teaVariety = teaVarietyService.updateTeaVariety(id, updatedTeaVariety);
        if (teaVariety != null) {
            return ResponseEntity.ok(teaVariety);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete tea variety by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeaVariety(@PathVariable Long id) {
        teaVarietyService.deleteTeaVariety(id);
        return ResponseEntity.ok().build();
    }

    // Get all tea varieties with pagination
    @GetMapping("/pagination")
    public Page<TeaVariety> getAllTeaVarietiesWithPagination(@RequestParam(defaultValue = "0") int page,
                                                             @RequestParam(defaultValue = "10") int size) {
        Page<TeaVariety> teaVarietyPage = teaVarietyService.getAllTeaVarietiesWithPagination(PageRequest.of(page, size));
        return teaVarietyPage;
    }

    // Search tea varieties by name with pagination
    @GetMapping("/search")
    public Page<TeaVariety> searchTeaVarietiesByName(@RequestParam String varietyName,
                                                     @RequestParam(defaultValue = "0") int page,
                                                     @RequestParam(defaultValue = "10") int size) {
        Page<TeaVariety> teaVarietyPage = teaVarietyService.searchTeaVarietiesByName(varietyName, PageRequest.of(page, size));
        return teaVarietyPage;
    }
}

