package com.walcfpw.department.service;

import reactor.core.publisher.Mono;

public interface DepartmentService {
    public Mono<String> hello();
}
