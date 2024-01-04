package com.springbootmicroservices.advertisement.service;

import com.springbootmicroservices.advertisement.dto.TeaFarmingImportDTO;
import com.springbootmicroservices.advertisement.entity.TeaFarmer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface TeaFarmerService {
    TeaFarmer createTeaFarmer(TeaFarmer teaFarmer);

    List<TeaFarmer> getAllTeaFarmers();

    TeaFarmer getTeaFarmerByGrowerNumber(String growerNumber);

    TeaFarmer updateTeaFarmer(String growerNumber, TeaFarmer updatedTeaFarmer);

    void deleteTeaFarmer(String growerNumber);

    Page<TeaFarmer> getAllTeaFarmersWithPagination(Pageable pageable);

    Page<TeaFarmer> searchTeaFarmersByName(String growerName, Pageable pageable);

    Page<TeaFarmer> searchTeaFarmers(String growerNumber, String growerName, String buyingCentre, String nationalId, String growerGroup, double totalLandAreaAcres, int page, int size);

    TeaFarmer importTeaFarmer(TeaFarmingImportDTO importRequest);
    void importData (MultipartFile file) throws IOException;
}

