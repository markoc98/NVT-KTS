package com.nwt_kts_project.CulturalOfferings.utility;


public interface MapperInterface<T,U> {

    T toEntity(U dto) throws Exception;

    U toDto(T entity);
}
