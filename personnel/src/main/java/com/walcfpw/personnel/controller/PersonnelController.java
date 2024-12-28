package com.walcfpw.personnel.controller;

import com.walcfpw.personnel.dto.PersonnelDTO;
import com.walcfpw.personnel.service.PersonnelService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

// @RequiredArgsConstructor makes the constructor for you and autowires it
@RequiredArgsConstructor
@RestController
@RequestMapping("/department")
public class PersonnelController {

    private final PersonnelService personnelService;

    @GetMapping("/hello")
    Mono<String> hello() {
        return personnelService.hello();
    }

    @PostMapping("/create")
    Mono<PersonnelDTO> createDepartment(@RequestBody PersonnelDTO personnelDTO) {
        return personnelService.createDepartment(personnelDTO);
    }

    @GetMapping("/id/{deptId}")
    Mono<PersonnelDTO> getDepartmentById(@PathVariable("deptId") Long deptId) {
        return personnelService.getDepartmentById(deptId);
    }

    @GetMapping("/name/{name}")
    Mono<PersonnelDTO> getDepartmentByName(@PathVariable("name") String name) {
        return personnelService.getDepartmentByName(name);
    }

    @GetMapping("/all")
    Flux<PersonnelDTO> getAllDepartments() {
        return personnelService.getAllDepartments();
    }

    @PutMapping("/{deptId}")
    Mono<PersonnelDTO> updateDepartment(@PathVariable("deptId") Long deptId, @RequestBody PersonnelDTO personnelDTO) {
        return personnelService.updateDepartment(deptId, personnelDTO);
    }

    @DeleteMapping("/{deptId}")
    Mono<Void> deleteDepartment(@PathVariable("deptId") Long deptId) {
        return personnelService.deleteDepartmentById(deptId);
    }
}
