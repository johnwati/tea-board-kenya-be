package com.springbootmicroservices.advertisement.service;

import com.springbootmicroservices.advertisement.entity.TeaCultivar;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TeaCultivarService {
    TeaCultivar createTeaCultivar(TeaCultivar teaCultivar);

    List<TeaCultivar> getAllTeaCultivars();

    TeaCultivar getTeaCultivarById(Long id);

    TeaCultivar updateTeaCultivar(Long id, TeaCultivar updatedTeaCultivar);

    void deleteTeaCultivar(Long id);

    Page<TeaCultivar> getAllTeaCultivarsWithPagination(Pageable pageable);

    Page<TeaCultivar> searchTeaCultivarsByName(String cultivarName, Pageable pageable);

    TeaCultivar createOrUpdateTeaCultivar(String teaCultivarName);
}
