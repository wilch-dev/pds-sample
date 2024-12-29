package com.walcfpw.personnel.service.impl;

import com.walcfpw.personnel.client.DepartmentClient;
import com.walcfpw.personnel.dto.DepartmentDTO;
import com.walcfpw.personnel.dto.PersonnelAndDepartmentDTO;
import com.walcfpw.personnel.dto.PersonnelDTO;
import com.walcfpw.personnel.dto.mapper.PersonnelAndDepartmentMapper;
import com.walcfpw.personnel.dto.mapper.PersonnelMapper;
import com.walcfpw.personnel.repository.PersonnelRepository;
import com.walcfpw.personnel.service.PersonnelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.ExecutionException;

@RequiredArgsConstructor
@Service
@Slf4j
public class PersonnelServiceImpl implements PersonnelService {

    private final PersonnelRepository personnelRepository;
    private final DepartmentClient departmentClient;

    public Mono<String> hello() {
        log.info("Logging - hello... It's working.");
        return Mono.just("Hello Reactive");
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

        Mono<PersonnelDTO> personnelDTO = getPersonnelById(personnelId);
        Mono<DepartmentDTO> departmentDTO = getDepartmentOfPersonnel(personnelDTO);

        try {
            return Mono.just(PersonnelAndDepartmentMapper.INSTANCE.toDto(personnelDTO.toFuture().(), departmentDTO.toFuture().get()));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Mono<DepartmentDTO> getDepartmentOfPersonnel(Mono<PersonnelDTO> personnelDTOMono) {
        try {
            return departmentClient.getDepartmentById(personnelDTOMono.toFuture().get().getDepartmentId());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
