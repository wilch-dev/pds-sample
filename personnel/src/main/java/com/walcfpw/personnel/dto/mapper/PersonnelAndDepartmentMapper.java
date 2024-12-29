package com.walcfpw.personnel.dto.mapper;

import com.walcfpw.personnel.dto.DepartmentDTO;
import com.walcfpw.personnel.dto.PersonnelAndDepartmentDTO;
import com.walcfpw.personnel.dto.PersonnelDTO;
import com.walcfpw.personnel.repository.entity.PersonnelEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PersonnelAndDepartmentMapper {
    PersonnelAndDepartmentMapper INSTANCE = Mappers.getMapper( PersonnelAndDepartmentMapper.class );
    PersonnelAndDepartmentDTO toDto(PersonnelDTO personnelDTO, DepartmentDTO departmentDTO);
}
