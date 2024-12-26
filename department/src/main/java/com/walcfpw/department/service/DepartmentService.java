package com.walcfpw.department.service;

import com.walcfpw.department.dto.DepartmentDTO;
import reactor.core.publisher.Mono;

public interface DepartmentService {
    Mono<String> hello();
    Mono<DepartmentDTO> dummyDepartment();

    Mono<DepartmentDTO> createDepartment(DepartmentDTO departmentRecord);

    Mono<DepartmentDTO> getDepartmentById(Long id);
    Mono<DepartmentDTO> getDepartmentByName(String name);

    Mono<DepartmentDTO> updateDepartment();
    Mono<DepartmentDTO> deleteDepartment();
}
