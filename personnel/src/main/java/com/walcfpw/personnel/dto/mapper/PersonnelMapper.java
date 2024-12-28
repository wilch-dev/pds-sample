package com.walcfpw.personnel.dto.mapper;

import com.walcfpw.personnel.dto.PersonnelDTO;
import com.walcfpw.personnel.repository.entity.PersonnelEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PersonnelMapper {
    PersonnelMapper INSTANCE = Mappers.getMapper( PersonnelMapper.class );
    PersonnelDTO toDto(PersonnelEntity personnelEntity);
    PersonnelEntity toEntity(PersonnelDTO personnelDTO);
}
