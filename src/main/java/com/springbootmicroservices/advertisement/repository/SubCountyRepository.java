package com.springbootmicroservices.advertisement.repository;

import com.springbootmicroservices.advertisement.entity.SubCounty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubCountyRepository extends JpaRepository<SubCounty, Long> {
    Page<SubCounty> findBySubCountyNameContainingIgnoreCase(String subCountyName, Pageable pageable);

    Optional<SubCounty> findBySubCountyNameContainingIgnoreCase(String subCountyName);
}
