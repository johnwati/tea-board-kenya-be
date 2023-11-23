package com.springbootmicroservices.advertisement.service.impl;

import com.springbootmicroservices.advertisement.entity.FarmingType;
import com.springbootmicroservices.advertisement.repository.FarmingTypeRepository;
import com.springbootmicroservices.advertisement.service.FarmingTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FarmingTypeServiceImpl implements FarmingTypeService {
    @Autowired
    private FarmingTypeRepository farmingTypeRepository;


    @Override
    public FarmingType createFarmingType(FarmingType farmingType) {
        return farmingTypeRepository.save(farmingType);
    }

    @Override
    public List<FarmingType> getAllFarmingTypes() {
        return farmingTypeRepository.findAll();
    }

    @Override
    public FarmingType getFarmingTypeById(Long id) {
        return farmingTypeRepository.findById(id).orElse(null);
    }

    @Override
    public FarmingType updateFarmingType(Long id, FarmingType updatedFarmingType) {
        updatedFarmingType.setFarmingTypeID(id);
        return farmingTypeRepository.save(updatedFarmingType);
    }

    @Override
    public void deleteFarmingType(Long id) {
        farmingTypeRepository.deleteById(id);
    }

    @Override
    public Page<FarmingType> getAllFarmingTypesWithPagination(Pageable pageable) {
        return farmingTypeRepository.findAll(pageable);
    }

    @Override
    public Page<FarmingType> searchFarmingTypesByName(String farmingTypeName, Pageable pageable) {
        return farmingTypeRepository.findByFarmingTypeNameContainingIgnoreCase(farmingTypeName, pageable);
    }

    @Override
    public FarmingType createOrUpdateFarmingType(String farmingTypeName) {
        // Check if the FarmingType already exists
        Optional<FarmingType> existingFarmingType = farmingTypeRepository.findByFarmingTypeNameContainingIgnoreCase(farmingTypeName);

        if (existingFarmingType.isPresent()) {
            // Update the existing FarmingType if found
            FarmingType foundFarmingType = existingFarmingType.get();
            // Update any fields if needed
            // foundFarmingType.setFieldToUpdate(newValue);

            return farmingTypeRepository.save(foundFarmingType);
        } else {
            // Create a new FarmingType if it doesn't exist
            FarmingType newFarmingType = new FarmingType();
            newFarmingType.setFarmingTypeName(farmingTypeName);

            return farmingTypeRepository.save(newFarmingType);
        }
    }
}
