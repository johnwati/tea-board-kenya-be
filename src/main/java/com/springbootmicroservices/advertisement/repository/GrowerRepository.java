package com.springbootmicroservices.advertisement.repository;

import com.springbootmicroservices.advertisement.entity.Grower;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GrowerRepository extends JpaRepository<Grower, Long> {
    // You can define custom query methods here if needed
    // For example, findGrowerByGrowerNumber(String growerNumber);
}
