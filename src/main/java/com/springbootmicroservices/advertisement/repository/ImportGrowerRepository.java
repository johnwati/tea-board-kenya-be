package com.springbootmicroservices.advertisement.repository;

import com.springbootmicroservices.advertisement.entity.TeaFarmer;
import com.springbootmicroservices.advertisement.service.GrowerDataImport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImportGrowerRepository extends JpaRepository<TeaFarmer, Long> {
    // You can define custom query methods here if needed
    // For example, findGrowerByGrowerNumber(String growerNumber);
}
