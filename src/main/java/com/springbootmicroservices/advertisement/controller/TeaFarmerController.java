package com.springbootmicroservices.advertisement.controller;

import com.springbootmicroservices.advertisement.dto.TeaFarmerDTO;
import com.springbootmicroservices.advertisement.dto.TeaFarmingImportDTO;
import com.springbootmicroservices.advertisement.entity.TeaFarmer;
import com.springbootmicroservices.advertisement.mappers.TeaFarmerMapper;
import com.springbootmicroservices.advertisement.service.TeaFarmerService;
import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin // Allow all origins for all methods in this controller
@RestController
@RequestMapping("/tea-farmers")
public class TeaFarmerController {
    private static final Logger logger = LoggerFactory.getLogger(TeaFarmerController.class);
    private final TeaFarmerMapper teaFarmerMapper;
    private final TeaFarmerService teaFarmerService;

    public TeaFarmerController(TeaFarmerService teaFarmerService) {
        this.teaFarmerService = teaFarmerService;
        this.teaFarmerMapper = Mappers.getMapper(TeaFarmerMapper.class);
    }

    @PostMapping("/create-multiple")
    public List<TeaFarmerDTO> createMultipleTeaFarmers(@RequestBody List<TeaFarmerDTO> teaFarmers) {
        List<TeaFarmerDTO> createdTeaFarmers = new ArrayList<>();
        for (TeaFarmerDTO teaFarmerDTO : teaFarmers) {
            TeaFarmer teaFarmer = this.teaFarmerMapper.teaFarmerDTOToEntity(teaFarmerDTO);
            createdTeaFarmers.add(this.teaFarmerMapper.teaFarmerToDTO(this.teaFarmerService.createTeaFarmer(teaFarmer)));
        }
        return createdTeaFarmers;
    }

    @PostMapping
    public TeaFarmerDTO createTeaFarmer(@RequestBody TeaFarmerDTO teaFarmerDTO) {
        TeaFarmer teaFarmer = this.teaFarmerMapper.teaFarmerDTOToEntity(teaFarmerDTO);
        return this.teaFarmerMapper.teaFarmerToDTO(this.teaFarmerService.createTeaFarmer(teaFarmer));
    }


    // Update tea farmer by grower number
    @PutMapping("/{growerNumber}")
    public ResponseEntity<TeaFarmer> updateTeaFarmer(@PathVariable String growerNumber, @RequestBody TeaFarmerDTO teaFarmerDTO) {
        TeaFarmer updatedTeaFarmer = this.teaFarmerMapper.teaFarmerDTOToEntity(teaFarmerDTO);
        TeaFarmer teaFarmer = this.teaFarmerService.updateTeaFarmer(growerNumber, updatedTeaFarmer);
        if (teaFarmer != null) {
            return ResponseEntity.ok(teaFarmer);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete tea farmer by grower number
    @DeleteMapping("/{growerNumber}")
    public ResponseEntity<Void> deleteTeaFarmer(@PathVariable String growerNumber) {
        this.teaFarmerService.deleteTeaFarmer(growerNumber);
        return ResponseEntity.ok().build();
    }

    // Get all tea farmers with pagination
    @GetMapping("/pagination")
    public Page<TeaFarmer> getAllTeaFarmersWithPagination(@RequestParam(defaultValue = "0") int page,
                                                          @RequestParam(defaultValue = "10") int size) {
        Page<TeaFarmer> teaFarmerPage = this.teaFarmerService.getAllTeaFarmersWithPagination(PageRequest.of(page, size));
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
        return this.teaFarmerService.searchTeaFarmers(growerNumber, growerName, buyingCentre, nationalId,
                growerGroup, totalLandAreaAcres, page, size);
    }

    @PostMapping("/import")
    public ResponseEntity<?> importTeaFarmers(@RequestBody List<TeaFarmingImportDTO> importRequests) {
        try {
            logger.info("------- IMPORTING farmers");

            List<TeaFarmer> importedFarmers = new ArrayList<>();
            for (TeaFarmingImportDTO importRequest : importRequests) {
                TeaFarmer teaFarmer = this.teaFarmerService.importTeaFarmer(importRequest);
                importedFarmers.add(teaFarmer);
            }

            return ResponseEntity.ok(importedFarmers);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error importing tea farmers: " + e);
        }
    }


    @PostMapping("/import-excel")
    public ResponseEntity importExcel(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("File is empty");
            String redirectUrl = "http://134.209.178.235:8080/administrator/tea_farmer";
            // Redirect to the specified URL using 'redirect:' prefix
//            return "redirect:http://134.209.178.235:8080/administrator/tea_farmer";
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(URI.create(redirectUrl));
            logger.info("Empty File");
            return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
        }
        try {
            this.teaFarmerService.importData(file);
//            return ResponseEntity.ok("Data imported successfully");
            // Redirect to the specified URL after importing data
//            String redirectUrl = "http://134.209.178.235:8080/administrator/tea_farmer";
//            // Redirect to the specified URL using 'redirect:' prefix
//            return "redirect:http://134.209.178.235:8080/administrator/tea_farmer";
            String redirectUrl = "http://134.209.178.235:8080/administrator/tea_farmer";
            // Redirect to the specified URL using 'redirect:' prefix
//            return "redirect:http://134.209.178.235:8080/administrator/tea_farmer";
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(URI.create(redirectUrl));
            logger.info("Finished Data Import");
            return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to import data" + e.getLocalizedMessage());
//            String redirectUrl = "http://134.209.178.235:8080/administrator/tea_farmer";
//            // Redirect to the specified URL using 'redirect:' prefix
//            return "redirect:http://134.209.178.235:8080/administrator/tea_farmer";
            logger.info(e.getMessage().toString());
            String redirectUrl = "http://134.209.178.235:8080/administrator/tea_farmer";
            // Redirect to the specified URL using 'redirect:' prefix
//            return "redirect:http://134.209.178.235:8080/administrator/tea_farmer";
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(URI.create(redirectUrl));
            return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
        }
    }

}
