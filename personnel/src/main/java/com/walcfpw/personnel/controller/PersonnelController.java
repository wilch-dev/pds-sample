package com.walcfpw.personnel.controller;

import com.walcfpw.personnel.dto.DepartmentDTO;
import com.walcfpw.personnel.dto.PersonnelAndDepartmentDTO;
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

    @GetMapping("/hello")
    Mono<String> hello() {
        return personnelService.hello();
    }

    @GetMapping("/getdept")
    Mono<DepartmentDTO> getdept(){
        return personnelService.getDepartment();
    }

    @PostMapping("/add")
    Mono<PersonnelDTO> addPersonnel(@RequestBody PersonnelDTO personnelDTO) {
        return personnelService.addPersonnel(personnelDTO);
    }

    @GetMapping("/id/{personnelId}")
    Mono<PersonnelDTO> getPersonnelById(@PathVariable("personnelId") Long deptId) {
        return personnelService.getPersonnelById(deptId);
    }

    @GetMapping("/all")
    Flux<PersonnelDTO> getAllPersonnel() {
        return personnelService.getAllPersonnel();
    }

    @PutMapping("/{personnelId}")
    Mono<PersonnelDTO> updatePersonnel(@PathVariable("personnelId") Long personnelId, @RequestBody PersonnelDTO personnelDTO) {
        return personnelService.updatePersonnel(personnelId, personnelDTO);
    }

    @DeleteMapping("/{personnelId}")
    Mono<Void> deletePersonnel(@PathVariable("personnelId") Long personnelId) {
        return personnelService.deletePersonnelById(personnelId);
    }

    @GetMapping("pand/{personnelId}")
    Mono<PersonnelAndDepartmentDTO> getPersonnelAndDepartment(@PathVariable("personnelId") Long personnelId) {
        return personnelService.getPersonnelByIdWithDepartment(personnelId);
    }
}
