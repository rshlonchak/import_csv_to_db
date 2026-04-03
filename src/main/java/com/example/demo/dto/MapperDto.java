package com.example.demo.dto;

import com.example.demo.model.OpenCellId;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MapperDto {
    MapperDto INSTANCE = Mappers.getMapper(MapperDto.class);

    OpenCellId toEntity(OpenCellIdDto dto);
}
