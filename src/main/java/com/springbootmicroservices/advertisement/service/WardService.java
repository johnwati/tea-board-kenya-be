package com.springbootmicroservices.advertisement.service;

import com.springbootmicroservices.advertisement.entity.SubCounty;
import com.springbootmicroservices.advertisement.entity.Ward;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface WardService {
    Ward createWard(Ward ward);

    List<Ward> getAllWards();

    Ward getWardById(Long id);

    Ward updateWard(Long id, Ward updatedWard);

    void deleteWard(Long id);

    Page<Ward> getAllWardsWithPagination(Pageable pageable);

    Page<Ward> searchWardsByName(String wardName, Pageable pageable);

    Ward createOrUpdateWard(String wardName, SubCounty subCounty);
}
