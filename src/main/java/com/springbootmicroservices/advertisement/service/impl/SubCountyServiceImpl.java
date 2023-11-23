package com.springbootmicroservices.advertisement.service.impl;

import com.springbootmicroservices.advertisement.entity.County;
import com.springbootmicroservices.advertisement.entity.SubCounty;
import com.springbootmicroservices.advertisement.repository.SubCountyRepository;
import com.springbootmicroservices.advertisement.service.SubCountyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubCountyServiceImpl implements SubCountyService {
    @Autowired
    private SubCountyRepository subCountyRepository;

    @Override
    public SubCounty createSubCounty(SubCounty subCounty) {
        return subCountyRepository.save(subCounty);
    }

    @Override
    public List<SubCounty> getAllSubCounties() {
        return subCountyRepository.findAll();
    }

    @Override
    public SubCounty getSubCountyById(Long id) {
        return subCountyRepository.findById(id).orElse(null);
    }

    @Override
    public SubCounty updateSubCounty(Long id, SubCounty updatedSubCounty) {
        updatedSubCounty.setSubCountyID(id);
        return subCountyRepository.save(updatedSubCounty);
    }

    @Override
    public void deleteSubCounty(Long id) {
        subCountyRepository.deleteById(id);
    }

    @Override
    public Page<SubCounty> getAllSubCountiesWithPagination(Pageable pageable) {
        return subCountyRepository.findAll(pageable);
    }

    @Override
    public Page<SubCounty> searchSubCountiesByName(String subCountyName, Pageable pageable) {
        return subCountyRepository.findBySubCountyNameContainingIgnoreCase(subCountyName, pageable);
    }

    @Override
    public SubCounty createOrUpdateSubCounty(String subCountyName, County county) {
        // Check if the SubCounty already exists for the given County
        Optional<SubCounty> existingSubCounty = subCountyRepository.findBySubCountyNameContainingIgnoreCase(subCountyName);

        if (existingSubCounty.isPresent()) {
            // Update the existing SubCounty if found
            SubCounty foundSubCounty = existingSubCounty.get();
            // Update any fields if needed
            // foundSubCounty.setFieldToUpdate(newValue);
            return subCountyRepository.save(foundSubCounty);
        } else {
            // Create a new SubCounty if it doesn't exist
            SubCounty newSubCounty = new SubCounty();
            newSubCounty.setSubCountyName(subCountyName);
            newSubCounty.setCounty(county);
            return subCountyRepository.save(newSubCounty);
        }
    }
}

