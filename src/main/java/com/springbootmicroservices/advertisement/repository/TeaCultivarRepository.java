package com.springbootmicroservices.advertisement.repository;

import com.springbootmicroservices.advertisement.entity.TeaCultivar;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeaCultivarRepository extends JpaRepository<TeaCultivar, Long> {
    Page<TeaCultivar> findByCultivarNameContainingIgnoreCase(String cultivarName, Pageable pageable);
    List<TeaCultivar> findByCultivarNameContainingIgnoreCase(String cultivarNames);
    TeaCultivar findFistByCultivarNameContainingIgnoreCase(String teaCultivarName);
}
