package com.walcfpw.department.repository.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table("department")
public class DepartmentEntity implements Serializable {
    @Id
    private Long id;
    private String name;
    private String assignedLocation;
}
