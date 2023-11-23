package com.springbootmicroservices.advertisement.service;

import com.springbootmicroservices.advertisement.entity.Factory;
import com.springbootmicroservices.advertisement.entity.Ward;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FactoryService {
    Factory createFactory(Factory factory);

    List<Factory> getAllFactories();

    Page<Factory> getAllFactories(Pageable pageable);

    Factory getFactoryById(Long id);

    Factory updateFactory(Long id, Factory updatedFactory);

    void deleteFactory(Long id);

    List<Factory> searchFactoriesByName(String factoryName);

    Page<Factory> searchFactoriesByName(String factoryName, Pageable pageable);

    Page<Factory> getAllFactoriesWithPagination(Pageable pageable);

    Page<Factory> searchFactoriesByFactoryName(String factoryName, Pageable pageable);

    Factory createOrUpdateFactory(String factoryName, Ward ward);
}

