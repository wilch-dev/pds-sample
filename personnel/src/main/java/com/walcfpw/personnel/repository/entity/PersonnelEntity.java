package com.walcfpw.personnel.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table("personnel")
public class PersonnelEntity implements Serializable {
    private UUID uuid;
    @Id
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate birthdate;
    private String nationality;
    private Long departmentId;
}
