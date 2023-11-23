package com.springbootmicroservices.advertisement.service;

import com.springbootmicroservices.advertisement.entity.FarmingType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FarmingTypeService {
    FarmingType createFarmingType(FarmingType farmingType);

    List<FarmingType> getAllFarmingTypes();

    FarmingType getFarmingTypeById(Long id);

    FarmingType updateFarmingType(Long id, FarmingType updatedFarmingType);

    void deleteFarmingType(Long id);

    Page<FarmingType> getAllFarmingTypesWithPagination(Pageable pageable);

    Page<FarmingType> searchFarmingTypesByName(String farmingTypeName, Pageable pageable);

    FarmingType createOrUpdateFarmingType(String farmingTypeName);
}
