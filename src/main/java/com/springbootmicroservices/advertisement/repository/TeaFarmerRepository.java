package com.springbootmicroservices.advertisement.repository;

import com.springbootmicroservices.advertisement.entity.TeaFarmer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeaFarmerRepository extends JpaRepository<TeaFarmer, String>, JpaSpecificationExecutor<TeaFarmer> {
    Page<TeaFarmer> findByGrowerNameContainingIgnoreCase(String growerName, Pageable pageable);
    List<TeaFarmer> findByGrowerNumberIgnoreCase(String growerNumber);


}
