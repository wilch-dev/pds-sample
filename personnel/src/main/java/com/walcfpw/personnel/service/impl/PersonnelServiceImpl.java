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

        return null;
//                getPersonnelById(personnelId).flatMap(personnelDTO -> {
//            return Mono.just(PersonnelAndDepartmentDTO.builder().personnelDTO(personnelDTO).build());
//        }).flatMap(personnelAndDepartmentDTO -> {
//            getDepartmentOfPersonnel(personnelAndDepartmentDTO.getPersonnelDTO().getDepartmentId());
//        });

//        PersonnelDTO personnelDTO;
//        DepartmentDTO departmentDTO;
//
//        getPersonnelById(personnelId).subscribeOn();
//
//        return Mono.just(new PersonnelAndDepartmentDTO()).doOnSubscribe(subscription -> {
//            PersonnelDTO personnelDTO = getPersonnelById(personnelId).block();
//            DepartmentDTO departmentDTO = departmentClient.getDepartmentById(personnelDTO.getDepartmentId()).block();
//            personnelAnd
//        })
//
//        return
//        PersonnelDTO personnelEntity = getPersonnelById(personnelId).toFuture().get()
//
//
//        return PersonnelAndDepartmentMapper.INSTANCE.toDto(personnelEntity,departmentDTO);

//        https://stackoverflow.com/a/58445824
    }
//
//    @Override
//    public Mono<DepartmentDTO> getDepartmentOfPersonnel(Mono<PersonnelDTO> personnelDTOMono) {
//        return null;
//    }

    @Override
    public Mono<DepartmentDTO> getDepartmentOfPersonnel(Long departmentId) {
        return departmentClient.getDepartmentById(departmentId);
    }
}
