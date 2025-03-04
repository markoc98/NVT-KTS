package com.nwt_kts_project.CulturalOfferings.service;

import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ServiceInterface<T> {
    List<T> findAll();

    T findOne(Long id);

    T create(T entity) throws Exception;

    T update(T entity, Long id) throws Exception;

    void delete(Long id) throws Exception;
}