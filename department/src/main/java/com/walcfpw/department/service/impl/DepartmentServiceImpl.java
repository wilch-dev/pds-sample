package com.walcfpw.department.service.impl;

import com.walcfpw.department.model.Department;
import com.walcfpw.department.service.DepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class DepartmentServiceImpl implements DepartmentService {

//    private static final Logger log = LoggerFactory.getLogger(DepartmentServiceImpl.class);

    @Override
    public Mono<String> hello() {
        log.info("Logging - hello... It's working.");
        return Mono.just("Hello Reactive");
    }

    @Override
    public Mono<Department> dummyDepartment() {
        return Mono.just(Department.builder().id(1L).assignedLocation("asdf").name("names").build());
    }
}
