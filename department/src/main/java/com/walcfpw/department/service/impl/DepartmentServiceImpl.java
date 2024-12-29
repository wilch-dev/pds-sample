package com.walcfpw.department.service.impl;

import com.walcfpw.department.dto.DepartmentDTO;
import com.walcfpw.department.dto.mapper.DepartmentMapper;
import com.walcfpw.department.repository.DepartmentRepository;
import com.walcfpw.department.repository.entity.DepartmentEntity;
import com.walcfpw.department.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
@Slf4j
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final ReactiveRedisTemplate<String, DepartmentEntity> reactiveRedisTemplate;
//    private final ReactiveHashOperations<String, Integer, ProductDto> hashOperations;

    @Override
    public Mono<String> hello() {
        log.info("Logging - hello... It's working.");
        return Mono.just("Hello Reactive");
    }

    @Override
    public Mono<DepartmentDTO> createDepartment(DepartmentDTO departmentDTO) {
        return departmentRepository.save(DepartmentMapper.INSTANCE.toEntity(departmentDTO))
                .flatMap(departmentEntity -> Mono.just(DepartmentMapper.INSTANCE.toDto(departmentEntity)));
//        I think map would do here since it's just one operation.
    }

    @Override
    public Mono<DepartmentDTO> getDepartmentById(Long id) {
        return departmentRepository.findById(id).map(departmentEntity ->
                DepartmentMapper.INSTANCE.toDto(departmentEntity));
    }

    @Override
    public Mono<DepartmentDTO> getDepartmentByName(String name) {
        return departmentRepository.findByName(name).map(departmentEntity ->
                DepartmentMapper.INSTANCE.toDto(departmentEntity));
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
                .switchIfEmpty(departmentRepository.findAll()
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
        return departmentRepository.save(DepartmentEntity.builder()
                        .id(deptId)
                .name(departmentDTO.getName())
                .assignedLocation(departmentDTO.getAssignedLocation())
                .build()).map(departmentEntity -> new DepartmentDTO(departmentEntity.getId(),
                departmentEntity.getName(),
                departmentEntity.getAssignedLocation()));
    }

    @Override
    public Mono<Void> deleteDepartmentById(Long deptId) {
        return departmentRepository.deleteById(deptId);
    }
}
