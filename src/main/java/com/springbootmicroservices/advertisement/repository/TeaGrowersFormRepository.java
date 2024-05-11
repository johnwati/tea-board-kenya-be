package com.springbootmicroservices.advertisement.repository;

import com.springbootmicroservices.advertisement.entity.TeaGrowersForm;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeaGrowersFormRepository extends JpaRepository<TeaGrowersForm, Long> {
    List<TeaGrowersForm> findByProcessed(String processed);
}

