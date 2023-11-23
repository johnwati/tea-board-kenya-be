package com.springbootmicroservices.advertisement.controller;

import com.springbootmicroservices.advertisement.dto.TeaFarmerDTO;
import com.springbootmicroservices.advertisement.dto.TeaFarmingImportDTO;
import com.springbootmicroservices.advertisement.entity.TeaFarmer;
import com.springbootmicroservices.advertisement.mappers.TeaFarmerMapper;
import com.springbootmicroservices.advertisement.service.TeaFarmerService;
import com.springbootmicroservices.advertisement.service.impl.GrowerService;
import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin // Allow all origins for all methods in this controller
@RestController
@RequestMapping("/tea-farmers")
public class TeaFarmerController {
    private final TeaFarmerMapper teaFarmerMapper;
    private final TeaFarmerService teaFarmerService;
    private static final Logger logger = LoggerFactory.getLogger(TeaFarmerController.class);
    @Autowired
    private GrowerService growerService;

    public TeaFarmerController(TeaFarmerService teaFarmerService) {
        this.teaFarmerService = teaFarmerService;
        this.teaFarmerMapper = Mappers.getMapper(TeaFarmerMapper.class);
    }

    @PostMapping("/create-multiple")
    public List<TeaFarmerDTO> createMultipleTeaFarmers(@RequestBody List<TeaFarmerDTO> teaFarmers) {
        List<TeaFarmerDTO> createdTeaFarmers = new ArrayList<>();
        for (TeaFarmerDTO teaFarmerDTO : teaFarmers) {
            TeaFarmer teaFarmer = teaFarmerMapper.teaFarmerDTOToEntity(teaFarmerDTO);
            createdTeaFarmers.add(teaFarmerMapper.teaFarmerToDTO(teaFarmerService.createTeaFarmer(teaFarmer)));
        }
        return createdTeaFarmers;
    }

    @PostMapping
    public TeaFarmerDTO createTeaFarmer(@RequestBody TeaFarmerDTO teaFarmerDTO) {
        TeaFarmer teaFarmer = teaFarmerMapper.teaFarmerDTOToEntity(teaFarmerDTO);
        return teaFarmerMapper.teaFarmerToDTO(teaFarmerService.createTeaFarmer(teaFarmer));
    }


    // Update tea farmer by grower number
    @PutMapping("/{growerNumber}")
    public ResponseEntity<TeaFarmer> updateTeaFarmer(@PathVariable String growerNumber, @RequestBody TeaFarmer updatedTeaFarmer) {
        TeaFarmer teaFarmer = teaFarmerService.updateTeaFarmer(growerNumber, updatedTeaFarmer);
        if (teaFarmer != null) {
            return ResponseEntity.ok(teaFarmer);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete tea farmer by grower number
    @DeleteMapping("/{growerNumber}")
    public ResponseEntity<Void> deleteTeaFarmer(@PathVariable String growerNumber) {
        teaFarmerService.deleteTeaFarmer(growerNumber);
        return ResponseEntity.ok().build();
    }

    // Get all tea farmers with pagination
    @GetMapping("/pagination")
    public Page<TeaFarmer> getAllTeaFarmersWithPagination(@RequestParam(defaultValue = "0") int page,
                                                          @RequestParam(defaultValue = "10") int size) {
        Page<TeaFarmer> teaFarmerPage = teaFarmerService.getAllTeaFarmersWithPagination(PageRequest.of(page, size));
        return teaFarmerPage;
    }

    @GetMapping("/search")
    public Page<TeaFarmer> searchTeaFarmers(@RequestParam(required = false) String growerNumber,
                                            @RequestParam(required = false) String growerName,
                                            @RequestParam(required = false) String buyingCentre,
                                            @RequestParam(required = false) String nationalId,
                                            @RequestParam(required = false) String growerGroup,
                                            @RequestParam(required = false, defaultValue = "0") double totalLandAreaAcres,
                                            @RequestParam(defaultValue = "0") int page,
                                            @RequestParam(defaultValue = "10") int size) {
        return teaFarmerService.searchTeaFarmers(growerNumber, growerName, buyingCentre, nationalId,
                growerGroup, totalLandAreaAcres, page, size);
    }

    @PostMapping("/import")
    public ResponseEntity<?> importTeaFarmer(@RequestBody TeaFarmingImportDTO importRequest) {
        try {
            logger.info("------- IMPORTING farmers");
           TeaFarmer teaFarmer = teaFarmerService.importTeaFarmer(importRequest);
            return ResponseEntity.ok(teaFarmer);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error importing tea farmer: " + e);
        }
    }
    @PostMapping("/import-excel")
    public ResponseEntity<String> importExcel(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("File is empty");
        }
        try {
            growerService.importData(file);
            return ResponseEntity.ok("Data imported successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to import data"+e.getLocalizedMessage());
        }
    }

}
