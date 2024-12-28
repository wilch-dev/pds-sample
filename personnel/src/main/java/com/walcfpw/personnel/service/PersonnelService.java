package com.walcfpw.personnel.service;

import com.walcfpw.department.dto.DepartmentDTO;
import com.walcfpw.personnel.dto.PersonnelDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PersonnelService {
    Mono<String> hello();

    Mono<PersonnelDTO> createPersonnel(PersonnelDTO personnelDTO);

    Mono<PersonnelDTO> getDepartmentById(Long id);
    Mono<PersonnelDTO> getDepartmentByName(String name);
    Flux<PersonnelDTO> getAllDepartments();

    Mono<PersonnelDTO> updateDepartment(Long deptId, PersonnelDTO departmentDTO);

    Mono<Void> deleteDepartmentById(Long deptId);
}
