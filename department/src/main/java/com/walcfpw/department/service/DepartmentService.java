package com.walcfpw.department.service;

import com.walcfpw.department.dto.DepartmentDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface DepartmentService {
    Mono<String> hello();

    Mono<DepartmentDTO> createDepartment(DepartmentDTO departmentRecord);

    Mono<DepartmentDTO> getDepartmentById(Long id);
    Mono<DepartmentDTO> getDepartmentByName(String name);
    Flux<DepartmentDTO> getAllDepartments();

    Mono<DepartmentDTO> updateDepartment(Long deptId, DepartmentDTO departmentDTO);

    Mono<Void> deleteDepartmentById(Long deptId);
}
