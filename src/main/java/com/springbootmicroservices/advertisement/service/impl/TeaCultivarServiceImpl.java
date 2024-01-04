package com.springbootmicroservices.advertisement.service.impl;

import com.springbootmicroservices.advertisement.entity.TeaCultivar;
import com.springbootmicroservices.advertisement.repository.TeaCultivarRepository;
import com.springbootmicroservices.advertisement.service.TeaCultivarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeaCultivarServiceImpl implements TeaCultivarService {
    @Autowired
    private TeaCultivarRepository teaCultivarRepository;

    @Override
    public TeaCultivar createTeaCultivar(TeaCultivar teaCultivar) {
        return teaCultivarRepository.save(teaCultivar);
    }

    @Override
    public List<TeaCultivar> getAllTeaCultivars() {
        return teaCultivarRepository.findAll();
    }

    @Override
    public TeaCultivar getTeaCultivarById(Long id) {
        return teaCultivarRepository.findById(id).orElse(null);
    }

    @Override
    public TeaCultivar updateTeaCultivar(Long id, TeaCultivar updatedTeaCultivar) {
        updatedTeaCultivar.setCultivarID(id);
        return teaCultivarRepository.save(updatedTeaCultivar);
    }

    @Override
    public void deleteTeaCultivar(Long id) {
        teaCultivarRepository.deleteById(id);
    }

    @Override
    public Page<TeaCultivar> getAllTeaCultivarsWithPagination(Pageable pageable) {
        return teaCultivarRepository.findAll(pageable);
    }

    @Override
    public Page<TeaCultivar> searchTeaCultivarsByName(String cultivarName, Pageable pageable) {
        return teaCultivarRepository.findByCultivarNameContainingIgnoreCase(cultivarName, pageable);
    }

    @Override
    public TeaCultivar createOrUpdateTeaCultivar(String teaCultivarName) {
        // Check if the TeaVariety already exists
//        Optional<TeaCultivar> existingTeaVariety = Optional.ofNullable(teaCultivarRepository.findFistByCultivarNameContainingIgnoreCase(teaCultivarName));
        List<TeaCultivar> existingTeaVarieties = teaCultivarRepository.findByCultivarNameContainingIgnoreCase(teaCultivarName);

        if (!existingTeaVarieties.isEmpty()) {
            // Update the existing TeaVariety if found
            TeaCultivar foundTeaVariety = existingTeaVarieties.get(0);
            // Update any fields if needed
            // foundTeaVariety.setFieldToUpdate(newValue);

            return teaCultivarRepository.save(foundTeaVariety);
        } else {
            // Create a new TeaVariety if it doesn't exist
            TeaCultivar teaCultivar = new TeaCultivar();
            teaCultivar.setCultivarName(teaCultivarName);

            return teaCultivarRepository.save(teaCultivar);
        }
    }
}

