package com.springbootmicroservices.advertisement.service.impl;

import com.springbootmicroservices.advertisement.entity.TeaVariety;
import com.springbootmicroservices.advertisement.repository.TeaVarietyRepository;
import com.springbootmicroservices.advertisement.service.TeaVarietyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeaVarietyServiceImpl implements TeaVarietyService {
    @Autowired
    private TeaVarietyRepository teaVarietyRepository;


    @Override
    public TeaVariety createTeaVariety(TeaVariety teaVariety) {
        return teaVarietyRepository.save(teaVariety);
    }

    @Override
    public List<TeaVariety> getAllTeaVarieties() {
        return teaVarietyRepository.findAll();
    }

    @Override
    public TeaVariety getTeaVarietyById(Long id) {
        return teaVarietyRepository.findById(id).orElse(null);
    }

    @Override
    public TeaVariety updateTeaVariety(Long id, TeaVariety updatedTeaVariety) {
        updatedTeaVariety.setVarietyID(id);
        return teaVarietyRepository.save(updatedTeaVariety);
    }

    @Override
    public void deleteTeaVariety(Long id) {
        teaVarietyRepository.deleteById(id);
    }

    @Override
    public Page<TeaVariety> getAllTeaVarietiesWithPagination(Pageable pageable) {
        return teaVarietyRepository.findAll(pageable);
    }

    @Override
    public Page<TeaVariety> searchTeaVarietiesByName(String varietyName, Pageable pageable) {
        return teaVarietyRepository.findByVarietyNameContainingIgnoreCase(varietyName, pageable);
    }

    @Override
    public TeaVariety createOrUpdateTeaVariety(String teaVarietyName) {
        // Check if the TeaVariety already exists
        Optional<TeaVariety> existingTeaVariety = Optional.ofNullable(teaVarietyRepository.findFirstByVarietyNameContainingIgnoreCase(teaVarietyName));

        if (existingTeaVariety.isPresent()) {
            // Update the existing TeaVariety if found
            TeaVariety foundTeaVariety = existingTeaVariety.get();
            // Update any fields if needed
            // foundTeaVariety.setFieldToUpdate(newValue);

            return teaVarietyRepository.save(foundTeaVariety);
        } else {
            // Create a new TeaVariety if it doesn't exist
            TeaVariety newTeaVariety = new TeaVariety();
            newTeaVariety.setVarietyName(teaVarietyName);

            return teaVarietyRepository.save(newTeaVariety);
        }
    }
}
