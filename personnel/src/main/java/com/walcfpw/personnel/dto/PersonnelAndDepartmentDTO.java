package com.walcfpw.personnel.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PersonnelAndDepartmentDTO {
    private PersonnelDTO personnelDTO;
    private DepartmentDTO departmentDTO;
}
