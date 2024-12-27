package com.walcfpw.department.service.impl;

import com.walcfpw.department.dto.DepartmentDTO;
import com.walcfpw.department.repository.DepartmentRepository;
import com.walcfpw.department.repository.entity.DepartmentEntity;
import com.walcfpw.department.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
@Slf4j
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Override
    public Mono<String> hello() {
        log.info("Logging - hello... It's working.");
        return Mono.just("Hello Reactive");
    }

    @Override
    public Mono<DepartmentDTO> createDepartment(DepartmentDTO departmentDTO) {
        return departmentRepository.save(DepartmentEntity.builder()
                        .name(departmentDTO.getName())
                        .assignedLocation(departmentDTO.getAssignedLocation())
                        .build()).map(departmentEntity -> new DepartmentDTO(departmentEntity.getId(),
                        departmentEntity.getName(),
                        departmentEntity.getAssignedLocation()));
    }

    @Override
    public Mono<DepartmentDTO> getDepartmentById(Long id) {
        return departmentRepository.findById(id).map(departmentEntity ->
                        new DepartmentDTO(departmentEntity.getId(),
                                departmentEntity.getName(),
                                departmentEntity.getAssignedLocation()));
    }

    @Override
    public Mono<DepartmentDTO> getDepartmentByName(String name) {
        return departmentRepository.findByName(name).map(departmentEntity ->
                new DepartmentDTO(departmentEntity.getId(),
                        departmentEntity.getName(),
                        departmentEntity.getAssignedLocation()));
    }

    @Override
    public Flux<DepartmentDTO> getAllDepartments() {
        return departmentRepository.findAll().map(departmentEntity ->
                new DepartmentDTO(departmentEntity.getId(),
                        departmentEntity.getName(),
                        departmentEntity.getAssignedLocation()));
    }


    @Override
    public Mono<DepartmentDTO> updateDepartment(Long deptId, DepartmentDTO departmentDTO) {
        return departmentRepository.save(DepartmentEntity.builder()
                        .id(deptId)
                .name(departmentDTO.getName())
                .assignedLocation(departmentDTO.getAssignedLocation())
                .build()).map(departmentEntity -> new DepartmentDTO(departmentEntity.getId(),
                departmentEntity.getName(),
                departmentEntity.getAssignedLocation()));
    }

    @Override
    public Mono<Void> deleteDepartmentById(Long deptId) {
        return departmentRepository.deleteById(deptId);
    }
}
