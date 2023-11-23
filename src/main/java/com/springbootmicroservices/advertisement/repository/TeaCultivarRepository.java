package com.springbootmicroservices.advertisement.repository;

import com.springbootmicroservices.advertisement.entity.TeaCultivar;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeaCultivarRepository extends JpaRepository<TeaCultivar, Long> {
    Page<TeaCultivar> findByCultivarNameContainingIgnoreCase(String cultivarName, Pageable pageable);
}
