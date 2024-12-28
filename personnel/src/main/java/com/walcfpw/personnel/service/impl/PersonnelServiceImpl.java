package com.walcfpw.personnel.service.impl;

import com.walcfpw.department.dto.DepartmentDTO;
import com.walcfpw.department.repository.DepartmentRepository;
import com.walcfpw.department.repository.entity.DepartmentEntity;
import com.walcfpw.personnel.dto.PersonnelDTO;
import com.walcfpw.personnel.repository.PersonnelRepository;
import com.walcfpw.personnel.repository.entity.PersonnelEntity;
import com.walcfpw.personnel.service.PersonnelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RequiredArgsConstructor
@Service
@Slf4j
public class PersonnelServiceImpl implements PersonnelService {

    private final PersonnelRepository personnelRepository;

    public Mono<String> hello() {
        log.info("Logging - hello... It's working.");
        return Mono.just("Hello Reactive");
    }

    @Override
    public Mono<DepartmentDTO> createDepartment(DepartmentDTO departmentDTO) {
        return personnelRepository.save(DepartmentEntity.builder()
                        .name(departmentDTO.getName())
                        .assignedLocation(departmentDTO.getAssignedLocation())
                        .build()).flatMap(departmentEntity -> Mono.just(new DepartmentDTO(departmentEntity.getId(),
                departmentEntity.getName(),
                departmentEntity.getAssignedLocation())));
//        I think map would do here since it's just one operation.
    }

    @Override
    public Mono<DepartmentDTO> getDepartmentById(Long id) {
        return personnelRepository.findById(id).map(departmentEntity ->
                        new DepartmentDTO(departmentEntity.getId(),
                                departmentEntity.getName(),
                                departmentEntity.getAssignedLocation()));
    }

    @Override
    public Mono<DepartmentDTO> getDepartmentByName(String name) {
        return personnelRepository.findByName(name).map(departmentEntity ->
                new DepartmentDTO(departmentEntity.getId(),
                        departmentEntity.getName(),
                        departmentEntity.getAssignedLocation()));
    }

//    @Override
//    public Flux<DepartmentDTO> getAllDepartments() {
//        return departmentRepository.findAll().flatMap(departmentEntity ->
//                Mono.just(new DepartmentDTO(departmentEntity.getId(),
//                        departmentEntity.getName(),
//                        departmentEntity.getAssignedLocation())));
//    }

    @Override
    public Flux<DepartmentDTO> getAllDepartments() {
        return reactiveRedisTemplate.keys("department:*")
                // Fetching cached movies.
                .flatMap(key -> reactiveRedisTemplate.opsForValue().get(key))
                // If cache is empty, fetch the database for movies
                .switchIfEmpty(personnelRepository.findAll()
                        // Persisting the fetched movies in the cache.
                        .flatMap(departmentEntity ->
                                reactiveRedisTemplate
                                        .opsForValue()
                                        .set("department:" + departmentEntity.getId(), departmentEntity)
                        )
                        // Fetching the movies from the updated cache.
                        .thenMany(reactiveRedisTemplate.keys("department:*")
                                .flatMap(key -> reactiveRedisTemplate.opsForValue().get(key))
                        )
                        // convert to DTO
                ).flatMap(departmentEntity ->Mono.just(
                        new DepartmentDTO(departmentEntity.getId(),
                                departmentEntity.getName(),
                                departmentEntity.getAssignedLocation())));
    }


    @Override
    public Mono<DepartmentDTO> updateDepartment(Long deptId, DepartmentDTO departmentDTO) {
        return personnelRepository.save(DepartmentEntity.builder()
                        .id(deptId)
                .name(departmentDTO.getName())
                .assignedLocation(departmentDTO.getAssignedLocation())
                .build()).map(departmentEntity -> new DepartmentDTO(departmentEntity.getId(),
                departmentEntity.getName(),
                departmentEntity.getAssignedLocation()));
    }

    @Override
    public Mono<Void> deleteDepartmentById(Long deptId) {
        return personnelRepository.deleteById(deptId);
    }

    @Override
    public Mono<PersonnelDTO> addPersonnel(PersonnelDTO personnelDTO) {

        ModelMapper modelMapper = new ModelMapper();
        OrderDTO orderDTO = modelMapper.map(order, OrderDTO.class);

        return personnelRepository.save(PersonnelEntity.builder()
                        .uuid(UUID.randomUUID())
                        .firstName(personnelDTO.getFirstName())
                        .lastName(personnelDTO.getLastName())
                        .birthdate(personnelDTO.getBirthdate())
                        .nationality(personnelDTO.getNationality())
                        .departmentId(personnelDTO.getDepartmentId())
                        .build()).flatMap(personnelEntity -> Mono.just(new PersonnelDTO(personnelEntity.getUuid(),
                        personnelEntity.getId(),
                        personnelEntity.getFirstName(),
                        personnelEntity.getLastName(),
                        personnelEntity.getBirthdate(),
                        personnelEntity.getNationality(),
                        personnelEntity.getDepartmentId())));

    }

    @Override
    public Mono<PersonnelDTO> getPersonnelById(Long id) {
        return null;
    }

    @Override
    public Flux<PersonnelDTO> getAllPersonnel() {
        return null;
    }

    @Override
    public Mono<PersonnelDTO> updatePersonnel(Long personnelId, PersonnelDTO departmentDTO) {
        return null;
    }

    @Override
    public Mono<Void> deletePersonnelById(Long personnelId) {
        return null;
    }
}
