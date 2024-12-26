package com.walcfpw.department.repository;

import com.walcfpw.department.repository.entity.DepartmentEntity;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Mono;

public interface DepartmentRepository extends R2dbcRepository<DepartmentEntity, Long> {
    Mono<DepartmentEntity> findByName(String name);
}
