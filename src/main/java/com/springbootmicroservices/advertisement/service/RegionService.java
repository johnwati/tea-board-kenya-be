package com.springbootmicroservices.advertisement.service;

import com.springbootmicroservices.advertisement.entity.Region;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RegionService {
    Region createRegion(Region region);

    List<Region> getAllRegions();

    Region getRegionById(Long id);

    Region updateRegion(Long id, Region updatedRegion);

    void deleteRegion(Long id);

    Page<Region> getAllRegionsWithPagination(Pageable pageable);

    Page<Region> searchRegionsByName(String regionName, Pageable pageable);

    Region createOrUpdateRegion(String regionName);
}
