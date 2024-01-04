package com.springbootmicroservices.advertisement.service.impl;

import com.springbootmicroservices.advertisement.entity.Region;
import com.springbootmicroservices.advertisement.repository.RegionRepository;
import com.springbootmicroservices.advertisement.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegionServiceImpl implements RegionService {
    @Autowired
    private RegionRepository regionRepository;

    @Override
    public Region createRegion(Region region) {
        return regionRepository.save(region);
    }

    @Override
    public List<Region> getAllRegions() {
        return regionRepository.findAll();
    }

    @Override
    public Region getRegionById(Long id) {
        return regionRepository.findById(id).orElse(null);
    }

    @Override
    public Region updateRegion(Long id, Region updatedRegion) {
        updatedRegion.setRegionID(id);
        return regionRepository.save(updatedRegion);
    }

    @Override
    public void deleteRegion(Long id) {
        regionRepository.deleteById(id);
    }

    @Override
    public Page<Region> getAllRegionsWithPagination(Pageable pageable) {
        return regionRepository.findAll(pageable);
    }

    @Override
    public Page<Region> searchRegionsByName(String regionName, Pageable pageable) {
        return regionRepository.findByRegionNameContainingIgnoreCase(regionName, pageable);
    }

    @Override
    public Region createOrUpdateRegion(String regionName) {
        // Check if the Region already exists
        List<Region> existingRegion = regionRepository.findByRegionNameContainingIgnoreCase(regionName);

        if (!existingRegion.isEmpty()) {
            return existingRegion.get(0);
        } else {
            // Create a new Region if it doesn't exist
            Region newRegion = new Region();
            newRegion.setRegionName(regionName);

            return regionRepository.save(newRegion);
        }
    }
}

