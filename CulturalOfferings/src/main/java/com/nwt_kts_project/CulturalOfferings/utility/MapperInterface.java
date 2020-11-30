package com.nwt_kts_project.CulturalOfferings.utility;


public interface MapperInterface<T,U> {

    T toEntity(U dto);

    U toDto(T entity);
}
