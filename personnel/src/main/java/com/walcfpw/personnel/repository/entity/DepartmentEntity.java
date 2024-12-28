package com.walcfpw.personnel.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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
