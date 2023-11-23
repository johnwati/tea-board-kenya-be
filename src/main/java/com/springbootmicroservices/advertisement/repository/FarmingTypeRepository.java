package com.springbootmicroservices.advertisement.repository;

import com.springbootmicroservices.advertisement.entity.FarmingType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FarmingTypeRepository extends JpaRepository<FarmingType, Long> {
    Page<FarmingType> findByFarmingTypeNameContainingIgnoreCase(String farmingTypeName, Pageable pageable);

    Optional<FarmingType> findByFarmingTypeNameContainingIgnoreCase(String farmingTypeName);
}
