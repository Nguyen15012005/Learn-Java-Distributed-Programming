package iuh.fit;

import iuh.fit.core.dto.DepartmentDTO;
import iuh.fit.core.entity.Department;
import tools.jackson.databind.ObjectMapper;

import java.util.Map;

public class JacksonDemo2 {
    public static void main(String[] args) {

        ObjectMapper mapper = new ObjectMapper();

        Map<String, Object> map = Map.of(
                "dept_id", "CS",
                "name", "CS",
                "building", "V",
                "room", "V3.1",
                "dean", "Dr. Smith"
        );

        Department department = mapper.convertValue(map, Department.class);
        DepartmentDTO departmentDTO = mapper.convertValue(department, DepartmentDTO.class);

        System.out.println(department);
        System.out.println(departmentDTO);

//      Entity -> Map<String, Object> -> DTO
//      DTO -> Map<String, Object> -> entity
    }
}
