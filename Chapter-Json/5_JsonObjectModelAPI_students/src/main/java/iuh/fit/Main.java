package iuh.fit;

import iuh.fit.entity.Enrollment;
import iuh.fit.util.JsonUtil;

import java.util.List;
import java.util.Map;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Map<String, Object> map = JsonUtil.getStudentCourseData("data/data.json");
        List<Enrollment> enrollments = (List<Enrollment>) map.get("enrollments");
        enrollments.forEach(System.out::println);
        JsonUtil.writeToJson(map, "enrollments","data/nguyen23640731.json");
    }
}
