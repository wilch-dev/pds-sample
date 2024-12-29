package com.walcfpw.personnel.service.impl;

import com.walcfpw.personnel.dto.PersonnelDTO;
import com.walcfpw.personnel.repository.PersonnelRepository;
import com.walcfpw.personnel.repository.entity.PersonnelEntity;
import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
@ExtendWith(SpringExtension.class)
class PersonnelServiceImplTest {

    @InjectMocks
    private PersonnelServiceImpl personnelService;

    @Mock
    private PersonnelRepository personnelRepository;

    PersonnelEntity personnelEntity1;
    PersonnelEntity personnelEntity2;

    @BeforeEach
    void setUp() {

        personnelEntity1 = PersonnelEntity.builder()
                .uuid(UUID.randomUUID())
                .id(1L)
                .firstName("John")
                .lastName("Mangione")
                .birthdate(Date.from(LocalDate.of( 2000 , 2 , 11 ).atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .nationality("Italian")
                .build();

        personnelEntity2 = PersonnelEntity.builder()
                .uuid(UUID.randomUUID())
                .id(2L)
                .firstName("Lora")
                .lastName("Sanchez")
                .birthdate(Date.from(LocalDate.of( 1998 , 4 , 16 ).atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .nationality("American")
                .build();
    }

    //https://medium.com/@BPandey/writing-unit-test-in-reactive-spring-boot-application-32b8878e2f57
    @Test
    void getPersonnelById() {
        when(personnelRepository.findById(1L)).thenReturn(Mono.just(personnelEntity1));
        Mono<PersonnelDTO> personDTO = personnelService.getPersonnelById(1L);
        StepVerifier.create(personDTO)
                .consumeNextWith(person -> {
                    assertEquals(1L, person.getId());
                    assertEquals(personnelEntity1.getFirstName(), person.getFirstName());
                    assertEquals("Mangione", person.getLastName());
                })
                .verifyComplete();
    }

    @Test
    void getAllPersonnel() {
        when(personnelRepository.findAll()).thenReturn(Flux.just(personnelEntity1, personnelEntity2));
        Flux<PersonnelDTO> personFlux = personnelService.getAllPersonnel();
        StepVerifier.create(personFlux)
                .consumeNextWith(person -> {
                    assertEquals(1L, person.getId());
                    assertEquals(personnelEntity1.getFirstName(), person.getFirstName());
                    assertEquals("Mangione", person.getLastName());
                }).consumeNextWith(person -> {
                    assertEquals(2L, person.getId());
                    assertEquals(personnelEntity2.getFirstName(), person.getFirstName());
                    assertEquals("Sanchez", person.getLastName());
                })
                .verifyComplete();
    }
}