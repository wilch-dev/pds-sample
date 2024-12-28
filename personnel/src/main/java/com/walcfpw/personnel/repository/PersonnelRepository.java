package com.walcfpw.personnel.repository;

import com.walcfpw.personnel.repository.entity.PersonnelEntity;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface PersonnelRepository extends R2dbcRepository<PersonnelEntity, Long> {

}
