package iuh.fit;

import iuh.fit.core.entity.Department;
import tools.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

public class JacksonDemo {
    public static void main(String[] args) {

        ObjectMapper mapper = new ObjectMapper();

        Department department = Department
                .builder()
                .id("FIT")
                .name("FIT")
                .dean("Dr. Duy")
                .building("H")
                .room("H1")
                .build();

//        Manual
//        Map<String, Object> map = Map.of(
//                "dept_id", department.getId(),
//                "name", department.getName(),
//                "building", department.getBuilding(),
//                "room", department.getRoom(),
//                "dean", department.getDean()
//        );

        Map<String, Object> map = mapper.convertValue(department, Map.class);
        System.out.println(map);
    }
}
