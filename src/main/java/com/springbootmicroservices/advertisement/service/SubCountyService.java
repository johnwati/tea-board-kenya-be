package com.springbootmicroservices.advertisement.service;

import com.springbootmicroservices.advertisement.entity.County;
import com.springbootmicroservices.advertisement.entity.SubCounty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SubCountyService {
    SubCounty createSubCounty(SubCounty subCounty);

    List<SubCounty> getAllSubCounties();

    SubCounty getSubCountyById(Long id);

    SubCounty updateSubCounty(Long id, SubCounty updatedSubCounty);

    void deleteSubCounty(Long id);

    Page<SubCounty> getAllSubCountiesWithPagination(Pageable pageable);

    Page<SubCounty> searchSubCountiesByName(String subCountyName, Pageable pageable);

    SubCounty createOrUpdateSubCounty(String subCountyName, County county);
}
