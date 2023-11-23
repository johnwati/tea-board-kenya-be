package com.springbootmicroservices.advertisement.service;

import com.springbootmicroservices.advertisement.entity.County;
import com.springbootmicroservices.advertisement.entity.Region;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CountyService {
    County createCounty(County county);

    Page<County> getAllCounties(Pageable pageable);

    County getCountyById(Long id);

    County updateCounty(Long id, County updatedCounty);

    void deleteCounty(Long id);

    Page<County> searchCounties(String keyword, Pageable pageable);

    County createOrUpdateCounty(String countyName, Region region);
}
