package com.example.demo.dto;

public interface DtoMapper<T> {
    default T toEntity(OpenCellIdDto dto) { return  null; };
    default DtoMapper<T> fromEntity(T entity) {return null; };
}
