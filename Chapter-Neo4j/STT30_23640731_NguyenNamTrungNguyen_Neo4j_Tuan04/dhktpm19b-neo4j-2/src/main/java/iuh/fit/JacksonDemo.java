package iuh.fit;

import iuh.fit.core.entity.Department;
import tools.jackson.databind.ObjectMapper;

import java.util.Map;

public class JacksonDemo {
    public static void main(String[] args) {

        ObjectMapper mapper = new ObjectMapper();

        Department department = Department.builder()
                .id("FIT")
                .room("H1")
                .dean("Dr. Duy")
                .name("FIT")
                .building("H")
                .build();

//        Map<String, Object> map = Map.of("name", department.getName(),
//                "dept_id", department.getId(),
//                "dean", department.getDean(),
//                "room", department.getRoom(),
//                "building", department.getBuilding()
//        );

        Map<String, Object> map = mapper.convertValue(department, Map.class);

        System.out.println(map);
    }
}
