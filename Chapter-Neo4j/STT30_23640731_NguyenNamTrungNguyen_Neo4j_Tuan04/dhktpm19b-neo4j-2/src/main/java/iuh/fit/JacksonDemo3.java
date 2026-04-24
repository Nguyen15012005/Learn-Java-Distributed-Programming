package iuh.fit;

import iuh.fit.core.dto.DepartmentDTO;
import iuh.fit.core.entity.Department;
import iuh.fit.infrastructure.mapper.GenericDataMapper;
import iuh.fit.infrastructure.mapper.impl.JacksonDataMapper;
import tools.jackson.databind.ObjectMapper;

import java.util.Map;

public class JacksonDemo3 {
    public static void main(String[] args) {
        GenericDataMapper mapper = new JacksonDataMapper();

        Department department = Department.builder()
                .id("FIT")
                .room("H1")
                .dean("Dr. Duy")
                .name("FIT")
                .building("H")
                .build();

        DepartmentDTO departmentDTO = mapper.toObject(mapper.toMap(department), DepartmentDTO.class);
        System.out.println(departmentDTO);
    }
}
