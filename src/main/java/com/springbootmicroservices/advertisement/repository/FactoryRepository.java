package com.springbootmicroservices.advertisement.repository;


import com.springbootmicroservices.advertisement.entity.Factory;
import com.springbootmicroservices.advertisement.entity.Ward;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface FactoryRepository extends JpaRepository<Factory, Long> {
    List<Factory> findByFactoryNameContaining(String factoryName);

    Page<Factory> findByFactoryNameContaining(String factoryName, Pageable pageable);
//
//    Factory findFirstByFactoryNameContaining(String factoryName);
//
//    @Query(value = "SELECT f FROM Factory f WHERE f.factoryName = :factoryName")
//    Optional<Factory> findFirstByFactoryName(String factoryName);
//
//    @Query(value = "SELECT f FROM Factory f WHERE f.factoryName = :factoryName AND f.ward = :wardId")
//    List<Factory> findFirstByFactoryNameAndWardId(String factoryName, String wardId);
}
