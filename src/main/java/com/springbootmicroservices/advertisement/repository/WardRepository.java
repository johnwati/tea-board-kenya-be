package com.springbootmicroservices.advertisement.repository;

import com.springbootmicroservices.advertisement.entity.Ward;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WardRepository extends JpaRepository<Ward, Long> {
    Page<Ward> findByWardNameContainingIgnoreCase(String wardName, Pageable pageable);

    Optional<Ward> findByWardNameContainingIgnoreCase(String wardName);
}

