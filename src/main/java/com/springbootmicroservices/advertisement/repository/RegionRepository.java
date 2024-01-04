package com.springbootmicroservices.advertisement.repository;

import com.springbootmicroservices.advertisement.entity.Region;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RegionRepository extends JpaRepository<Region, Long> {

    Page<Region> findByRegionNameContainingIgnoreCase(String regionName, Pageable pageable);

//    Optional<Region> findByRegionNameContainingIgnoreCase(String regionName);

    List<Region> findByRegionNameContainingIgnoreCase(String regionName);
}
