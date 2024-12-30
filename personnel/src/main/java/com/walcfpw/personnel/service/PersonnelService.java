package com.walcfpw.personnel.service;

import com.walcfpw.personnel.dto.DepartmentDTO;
import com.walcfpw.personnel.dto.PersonnelAndDepartmentDTO;
import com.walcfpw.personnel.dto.PersonnelDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PersonnelService {

    Mono<String> hello();

    Mono<DepartmentDTO> getDepartment();

    Mono<PersonnelDTO> addPersonnel(PersonnelDTO personnelDTO);

    Mono<PersonnelDTO> getPersonnelById(Long id);
    Flux<PersonnelDTO> getAllPersonnel();

    Mono<PersonnelDTO> updatePersonnel(Long personnelId, PersonnelDTO departmentDTO);

    Mono<Void> deletePersonnelById(Long personnelId);

    Mono<PersonnelAndDepartmentDTO> getPersonnelByIdWithDepartment(Long personnelId);

    Mono<DepartmentDTO> getDepartmentOfPersonnel(Long personnelId);

//    Mono<DepartmentDTO> getDepartmentOfPersonnel(Mono<PersonnelDTO> personnelDTOMono);
}
