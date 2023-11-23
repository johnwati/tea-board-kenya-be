package com.springbootmicroservices.advertisement.repository;

import com.springbootmicroservices.advertisement.entity.County;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CountyRepository extends JpaRepository<County, Long> {

    Page<County> findByCountyNameContainingIgnoreCase(String keyword, Pageable pageable);

    Optional<County> findByCountyNameContainingIgnoreCase(String countyName);
}
