package com.walcfpw.department.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class DepartmentDTO {
    private Long id;
    private String name;
    private String assignedLocation;
}