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
@RequestMapping("/personnel")
public class PersonnelController {

    private final PersonnelService personnelService;

    @PostMapping("/add")
    Mono<PersonnelDTO> addPersonnel(@RequestBody PersonnelDTO personnelDTO) {
        return personnelService.addPersonnel(personnelDTO);
    }

    @GetMapping("/id/{personnelId}")
    Mono<PersonnelDTO> getPersonnelById(@PathVariable("personnelId") Long deptId) {
        return personnelService.getPersonnelById(deptId);
    }

    @GetMapping("/name/{name}")
    Mono<PersonnelDTO> getDepartmentByName(@PathVariable("name") String name) {
        return personnelService.getDepartmentByName(name);
    }

    @GetMapping("/all")
    Flux<PersonnelDTO> getAllDepartments() {
        return personnelService.getAllPersonnel();
    }

    @PutMapping("/{deptId}")
    Mono<PersonnelDTO> updateDepartment(@PathVariable("deptId") Long deptId, @RequestBody PersonnelDTO personnelDTO) {
        return personnelService.updatePersonnel(deptId, personnelDTO);
    }

    @DeleteMapping("/{deptId}")
    Mono<Void> deleteDepartment(@PathVariable("deptId") Long deptId) {
        return personnelService.deletePersonnelById(deptId);
    }
}
