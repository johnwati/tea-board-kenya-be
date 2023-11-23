package com.springbootmicroservices.advertisement.service;

import com.springbootmicroservices.advertisement.entity.TeaVariety;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TeaVarietyService {
    TeaVariety createTeaVariety(TeaVariety teaVariety);

    List<TeaVariety> getAllTeaVarieties();

    TeaVariety getTeaVarietyById(Long id);

    TeaVariety updateTeaVariety(Long id, TeaVariety updatedTeaVariety);

    void deleteTeaVariety(Long id);

    Page<TeaVariety> getAllTeaVarietiesWithPagination(Pageable pageable);

    Page<TeaVariety> searchTeaVarietiesByName(String varietyName, Pageable pageable);

    TeaVariety createOrUpdateTeaVariety(String teaVarietyName);
}
