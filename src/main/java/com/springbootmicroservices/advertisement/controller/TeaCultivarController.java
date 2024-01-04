package com.springbootmicroservices.advertisement.controller;

import com.springbootmicroservices.advertisement.entity.TeaCultivar;
import com.springbootmicroservices.advertisement.service.TeaCultivarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin // Allow all origins for all methods in this controller
@RestController
@RequestMapping("/teacultivars")
public class TeaCultivarController {
    @Autowired
    private TeaCultivarService teaCultivarService;


    // Create a new tea cultivar
    @PostMapping
    public TeaCultivar createTeaCultivar(@RequestBody TeaCultivar teaCultivar) {
        return teaCultivarService.createTeaCultivar(teaCultivar);
    }

    // Get all tea cultivars
    @GetMapping
    public List<TeaCultivar> getAllTeaCultivars() {
        return teaCultivarService.getAllTeaCultivars();
    }

    // Get tea cultivar by ID
    @GetMapping("/{id}")
    public ResponseEntity<TeaCultivar> getTeaCultivarById(@PathVariable Long id) {
        TeaCultivar teaCultivar = teaCultivarService.getTeaCultivarById(id);
        if (teaCultivar != null) {
            return ResponseEntity.ok(teaCultivar);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Update tea cultivar by ID
    @PutMapping("/{id}")
    public ResponseEntity<TeaCultivar> updateTeaCultivar(@PathVariable Long id, @RequestBody TeaCultivar updatedTeaCultivar) {
        TeaCultivar teaCultivar = teaCultivarService.updateTeaCultivar(id, updatedTeaCultivar);
        if (teaCultivar != null) {
            return ResponseEntity.ok(teaCultivar);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete tea cultivar by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeaCultivar(@PathVariable Long id) {
        teaCultivarService.deleteTeaCultivar(id);
        return ResponseEntity.ok().build();
    }

    // Get all tea cultivars with pagination
    @GetMapping("/pagination")
    public Page<TeaCultivar> getAllTeaCultivarsWithPagination(@RequestParam(defaultValue = "0") int page,
                                                              @RequestParam(defaultValue = "10") int size) {
        Page<TeaCultivar> teaCultivarPage = teaCultivarService.getAllTeaCultivarsWithPagination(PageRequest.of(page, size));
        return teaCultivarPage;
    }

    // Search tea cultivars by name with pagination
    @GetMapping("/search")
    public Page<TeaCultivar> searchTeaCultivarsByName(@RequestParam String cultivarName,
                                                      @RequestParam(defaultValue = "0") int page,
                                                      @RequestParam(defaultValue = "10") int size) {
        Page<TeaCultivar> teaCultivarPage = teaCultivarService.searchTeaCultivarsByName(cultivarName, PageRequest.of(page, size));
        return teaCultivarPage;
    }
}
