package com.walcfpw.personnel.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
@Data
@Builder
public class PersonnelDTO {
    private UUID uuid;
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate birthdate;
    private String nationality;
    private Long departmentId;
}