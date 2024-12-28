package com.walcfpw.department.dto.mapper;

import com.walcfpw.department.dto.DepartmentDTO;
import com.walcfpw.department.repository.entity.DepartmentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DepartmentMapper {
    DepartmentMapper INSTANCE = Mappers.getMapper( DepartmentMapper.class );
    DepartmentDTO toDto(DepartmentEntity personnelEntity);
    DepartmentEntity toEntity(DepartmentDTO personnelDTO);
}
