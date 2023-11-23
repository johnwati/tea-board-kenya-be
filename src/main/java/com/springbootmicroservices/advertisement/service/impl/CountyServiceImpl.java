package com.springbootmicroservices.advertisement.service.impl;

import com.springbootmicroservices.advertisement.entity.County;
import com.springbootmicroservices.advertisement.entity.Region;
import com.springbootmicroservices.advertisement.repository.CountyRepository;
import com.springbootmicroservices.advertisement.service.CountyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountyServiceImpl implements CountyService {
    @Autowired
    private CountyRepository countyRepository;

    @Override
    public County createCounty(County county) {
        return countyRepository.save(county);
    }

    @Override
    public Page<County> getAllCounties(Pageable pageable) {
        List<County> counties = countyRepository.findAll();
        return new PageImpl<>(counties, pageable, counties.size());
    }

    @Override
    public County getCountyById(Long id) {
        Optional<County> county = countyRepository.findById(id);
        return county.orElse(null);
    }

    @Override
    public County updateCounty(Long id, County updatedCounty) {
        Optional<County> existingCounty = countyRepository.findById(id);
        if (existingCounty.isPresent()) {
            updatedCounty.setCountyID(id);
            return countyRepository.save(updatedCounty);
        } else {
            return null;
        }
    }

    @Override
    public void deleteCounty(Long id) {
        countyRepository.deleteById(id);
    }

    public Page<County> searchCounties(String keyword, Pageable pageable) {
        return countyRepository.findByCountyNameContainingIgnoreCase(keyword, pageable);
    }

    @Override
    public County createOrUpdateCounty(String countyName, Region region) {
        // Check if the County already exists
        Optional<County> existingCounty = countyRepository.findByCountyNameContainingIgnoreCase(countyName);

        if (existingCounty.isPresent()) {
            // Update the existing County if found
            County foundCounty = existingCounty.get();
            // Update any fields if needed
            // foundCounty.setFieldToUpdate(newValue);
            return countyRepository.save(foundCounty);
        } else {
            // Create a new County if it doesn't exist
            County newCounty = new County();
            newCounty.setCountyName(countyName);
            newCounty.setRegion(region);
            return countyRepository.save(newCounty);
        }
    }

}
