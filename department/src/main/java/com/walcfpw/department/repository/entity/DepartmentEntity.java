package com.walcfpw.department.repository.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Builder
@Table("department")
public class DepartmentEntity {
    @Id
    private Long id;
    private String name;
    private String assignedLocation;
}
