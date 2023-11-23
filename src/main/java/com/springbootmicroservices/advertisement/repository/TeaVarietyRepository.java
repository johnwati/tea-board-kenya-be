package com.springbootmicroservices.advertisement.repository;

import com.springbootmicroservices.advertisement.entity.TeaVariety;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeaVarietyRepository extends JpaRepository<TeaVariety, Long> {
    Page<TeaVariety> findByVarietyNameContainingIgnoreCase(String varietyName, Pageable pageable);
    TeaVariety findByVarietyNameContainingIgnoreCase(String varietyName);
}
