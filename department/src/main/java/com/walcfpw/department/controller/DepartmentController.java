package com.walcfpw.department.controller;

import com.walcfpw.department.dto.DepartmentDTO;
import com.walcfpw.department.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

// @RequiredArgsConstructor makes the constructor for you and autowires it
@RequiredArgsConstructor
@RestController
@RequestMapping("/department")
public class DepartmentController {

    private final DepartmentService departmentService;

    @GetMapping("/hello")
    Mono<String> hello() {
        return departmentService.hello();
    }

    @GetMapping("/dummyDept")
    Mono<DepartmentDTO> dummyDept() {
        return departmentService.dummyDepartment();
    }

    @PostMapping("/create")
    Mono<DepartmentDTO> createDepartment(@RequestBody DepartmentDTO departmentDTO) {
        return departmentService.createDepartment(departmentDTO);
    }

    @GetMapping("/id/{deptId}")
    Mono<DepartmentDTO> getDepartmentById(@PathVariable("deptId") Long deptId) {
        return departmentService.getDepartmentById(deptId);
    }

    @GetMapping("/name/{name}")
    Mono<DepartmentDTO> getDepartmentByName(@PathVariable("name") String name) {
        return departmentService.getDepartmentByName(name);
    }

    @PutMapping("/{deptId}")
    Mono<DepartmentDTO> updateDepartment(@PathVariable("deptId") Long deptId, @RequestBody DepartmentDTO departmentDTO) {
        return departmentService.updateDepartment(deptId, departmentDTO);
    }

    @DeleteMapping("/{deptId}")
    Mono<Void> deleteDepartment(@PathVariable("deptId") Long deptId) {
        return departmentService.deleteDepartmentById(deptId);
    }
}
