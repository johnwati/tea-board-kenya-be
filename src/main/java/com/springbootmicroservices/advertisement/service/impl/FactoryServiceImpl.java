package com.springbootmicroservices.advertisement.service.impl;



import com.springbootmicroservices.advertisement.entity.Factory;
import com.springbootmicroservices.advertisement.entity.Ward;
import com.springbootmicroservices.advertisement.repository.FactoryRepository;
import com.springbootmicroservices.advertisement.service.FactoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class FactoryServiceImpl implements FactoryService {
    @Autowired
    private FactoryRepository factoryRepository;

    public FactoryServiceImpl(FactoryRepository factoryRepository) {
        this.factoryRepository = factoryRepository;
    }

    @Override
    public Factory createFactory(Factory factory) {
        return factoryRepository.save(factory);
    }

    @Override
    public List<Factory> getAllFactories() {
        return factoryRepository.findAll();
    }

    @Override
    public Page<Factory> getAllFactories(Pageable pageable) {
        return factoryRepository.findAll(pageable);
    }

    @Override
    public Factory getFactoryById(Long id) {
        Optional<Factory> factoryOptional = factoryRepository.findById(id);
        return factoryOptional.orElse(null);
    }

    @Override
    public Factory updateFactory(Long id, Factory updatedFactory) {
        if (factoryRepository.existsById(id)) {
            updatedFactory.setFactoryId(id);
            return factoryRepository.save(updatedFactory);
        } else {
            return null;
        }
    }

    @Override
    public void deleteFactory(Long id) {
        factoryRepository.deleteById(id);
    }

    @Override
    public List<Factory> searchFactoriesByName(String factoryName) {
        return factoryRepository.findByFactoryNameContaining(factoryName);
    }

    @Override
    public Page<Factory> searchFactoriesByName(String factoryName, Pageable pageable) {
        return factoryRepository.findByFactoryNameContaining(factoryName, pageable);
    }


    @Override
    public Page<Factory> getAllFactoriesWithPagination(Pageable pageable) {
        return factoryRepository.findAll(pageable);
    }

    @Override
    public Page<Factory> searchFactoriesByFactoryName(String factoryName, Pageable pageable) {
        return factoryRepository.findByFactoryNameContaining(factoryName, pageable);
    }

    @Transactional
    @Override
    public Factory createOrUpdateFactory(String factoryName, Ward ward) {
        Factory factory = factoryRepository.findByFactoryNameContaining(factoryName).stream().findFirst()
                .orElse(new Factory());
        factory.setFactoryName(factoryName);
        factory.setWard(ward);
        return factoryRepository.save(factory);
    }

}

