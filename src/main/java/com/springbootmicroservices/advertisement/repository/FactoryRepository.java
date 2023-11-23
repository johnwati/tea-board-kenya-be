package com.springbootmicroservices.advertisement.repository;



import com.springbootmicroservices.advertisement.entity.Factory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FactoryRepository extends JpaRepository<Factory, Long> {
    List<Factory> findByFactoryNameContaining(String factoryName);

    Page<Factory> findByFactoryNameContaining(String factoryName, Pageable pageable);
}
