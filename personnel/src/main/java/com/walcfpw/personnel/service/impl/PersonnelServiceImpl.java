package com.walcfpw.personnel.service.impl;

import com.walcfpw.personnel.client.DepartmentClient;
import com.walcfpw.personnel.dto.DepartmentDTO;
import com.walcfpw.personnel.dto.PersonnelAndDepartmentDTO;
import com.walcfpw.personnel.dto.PersonnelDTO;
import com.walcfpw.personnel.dto.mapper.PersonnelMapper;
import com.walcfpw.personnel.repository.PersonnelRepository;
import com.walcfpw.personnel.service.PersonnelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
@Slf4j
public class PersonnelServiceImpl implements PersonnelService {

    private final PersonnelRepository personnelRepository;
    private final DepartmentClient departmentClient;

    @Override
    public Mono<String> hello() {
//        log.info("Logging - hello... It's working.");
//        return Mono.just("Hello asdfasdf");
        return departmentClient.getHello();
    }

    @Override
    public Mono<DepartmentDTO> getDepartment(){
        return departmentClient.getDepartmentById(1L);
    }

    @Override
    public Mono<PersonnelDTO> addPersonnel(PersonnelDTO personnelDTO) {
        return personnelRepository.save(PersonnelMapper.INSTANCE.toEntity(personnelDTO))
                .flatMap(personnelEntity -> Mono.just(PersonnelMapper.INSTANCE.toDto(personnelEntity)));
    }

    @Override
    public Mono<PersonnelDTO> getPersonnelById(Long id) {
        return personnelRepository.findById(id).map(PersonnelMapper.INSTANCE::toDto);
    }

    @Override
    public Flux<PersonnelDTO> getAllPersonnel() {
        return personnelRepository.findAll().flatMap(personnelEntity ->
                Mono.just(PersonnelMapper.INSTANCE.toDto(personnelEntity)));
    }

    @Override
    public Mono<PersonnelDTO> updatePersonnel(Long personnelId, PersonnelDTO personnelDTO) {
        return personnelRepository.save(PersonnelMapper.INSTANCE.toEntity(personnelDTO))
                .flatMap(personnelEntity -> Mono.just(PersonnelMapper.INSTANCE.toDto(personnelEntity)));
    }

    @Override
    public Mono<Void> deletePersonnelById(Long personnelId) {
        return personnelRepository.deleteById(personnelId);
    }

    @Override
    public Mono<PersonnelAndDepartmentDTO> getPersonnelByIdWithDepartment(Long personnelId) {

        return getPersonnelById(personnelId).flatMap(personnelDTO ->
                        getDepartmentOfPersonnel(personnelDTO.getDepartmentId())
                                .map(departmentDTO ->
                                        new PersonnelAndDepartmentDTO(personnelDTO, departmentDTO)));

//        https://stackoverflow.com/questions/66832523/mixing-two-mono-and-return-first-one-with-second-in-firsts-body
    }


//    @Override
//    public Mono<PersonnelAndDepartmentDTO> getPersonnelByIdWithDepartment(Long personnelId) {
//
//        Mono<PersonnelAndDepartmentDTO> personnelAndDepartmentDTOMono =
//                Mono.zip(getPersonnelById(personnelId), getDepartmentOfPersonnel(1L));
//
//        return getPersonnelById(personnelId).flatMap(personnelDTO ->
//                getDepartmentOfPersonnel(personnelDTO.getDepartmentId())
//                        .map(departmentDTO ->
//                                new PersonnelAndDepartmentDTO(personnelDTO, departmentDTO)));
//    }

    @Override
    public Mono<DepartmentDTO> getDepartmentOfPersonnel(Long departmentId) {
        return departmentClient.getDepartmentById(departmentId);
    }
}
