package com.walcfpw.department.model;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class Department {

//    @UuidGenerator
//    UUID uuid;
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String assignedLocation;
}