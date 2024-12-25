package com.walcfpw.department.service;

import com.walcfpw.department.model.Department;
import reactor.core.publisher.Mono;

public interface DepartmentService {
    public Mono<String> hello();
    public Mono<Department> dummyDepartment();
}
