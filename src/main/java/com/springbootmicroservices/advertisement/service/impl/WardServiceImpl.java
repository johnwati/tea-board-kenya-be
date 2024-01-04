package com.springbootmicroservices.advertisement.service.impl;

import com.springbootmicroservices.advertisement.entity.SubCounty;
import com.springbootmicroservices.advertisement.entity.Ward;
import com.springbootmicroservices.advertisement.repository.WardRepository;
import com.springbootmicroservices.advertisement.service.WardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WardServiceImpl implements WardService {
    @Autowired
    private WardRepository wardRepository;

    @Override
    public Ward createWard(Ward ward) {
        return wardRepository.save(ward);
    }

    @Override
    public List<Ward> getAllWards() {
        return wardRepository.findAll();
    }

    @Override
    public Ward getWardById(Long id) {
        return wardRepository.findById(id).orElse(null);
    }

    @Override
    public Ward updateWard(Long id, Ward updatedWard) {
        updatedWard.setWardID(id);
        return wardRepository.save(updatedWard);
    }

    @Override
    public void deleteWard(Long id) {
        wardRepository.deleteById(id);
    }

    @Override
    public Page<Ward> getAllWardsWithPagination(Pageable pageable) {
        return wardRepository.findAll(pageable);
    }

    @Override
    public Page<Ward> searchWardsByName(String wardName, Pageable pageable) {
        return wardRepository.findByWardNameContainingIgnoreCase(wardName, pageable);
    }

    @Override
    public Ward createOrUpdateWard(String wardName, SubCounty subCounty) {
        // Check if the Ward already exists for the given SubCounty
        List<Ward> existingWard = wardRepository.findFirstByWardNameContainingIgnoreCase(wardName);

        if (!existingWard.isEmpty()) {
            return existingWard.get(0);
        } else {
            // Create a new Ward if it doesn't exist
            Ward newWard = new Ward();
            newWard.setWardName(wardName);
            newWard.setSubCounty(subCounty);

            return wardRepository.save(newWard);
        }
    }
}
