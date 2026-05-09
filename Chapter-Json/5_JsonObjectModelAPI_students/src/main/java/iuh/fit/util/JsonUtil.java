package iuh.fit.util;

import iuh.fit.entity.Course;
import iuh.fit.entity.Enrollment;
import iuh.fit.entity.Student;
import jakarta.json.*;
import jakarta.json.stream.JsonGenerator;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.awt.AWTEventMulticaster.add;

public class JsonUtil {
    public static Map<String, Object> getStudentCourseData(String fileName){
        Map<String, Object> res = new HashMap<>();

        try(JsonReader reader = Json.createReader(new FileReader(fileName))){
            JsonObject dataObject = reader.readObject();

            JsonArray studentJsonArray = dataObject.getJsonArray("students");
            List<Student> students = new ArrayList<>();
            Map<String, Student> studentMap = new HashMap<>();
            studentJsonArray.forEach(studentJsonValue -> {
                JsonObject studentJsonObject = studentJsonValue.asJsonObject();
                    String studentId = studentJsonObject.getString("studentId");
                    String name = studentJsonObject.getString("name");
                    Student student = new Student(studentId, name);
                    studentMap.put(student.getStudentId(), student);
                    students.add(student);
            });

            JsonArray courseJsonArray = dataObject.getJsonArray("courses");
            List<Course> courses = new ArrayList<>();
            Map<String, Course> courseMap = new HashMap<>();
            courseJsonArray.forEach(courseJsonValue -> {
                JsonObject courseJsonObject = courseJsonValue.asJsonObject();
                String courseId = courseJsonObject.getString("courseId");
                String courseName = courseJsonObject.getString("courseName");
                Course course = new Course(courseId, courseName);
                courseMap.put(course.getCourseId(), course);
                courses.add(course);
            });

            JsonArray enrollmentJsonArray = dataObject.getJsonArray("enrollments");
            List<Enrollment> enrollments = new ArrayList<>();
            enrollmentJsonArray.forEach(enrollmentJsonValue -> {
                JsonObject enrollmentJsonObject = enrollmentJsonValue.asJsonObject();
                String studentId = enrollmentJsonObject.getString("studentId");
                String courseId = enrollmentJsonObject.getString("courseId");
                String semester = enrollmentJsonObject.getString("semester");
                double grade = enrollmentJsonObject.getJsonNumber("grade").doubleValue();
                Enrollment enrollment = new Enrollment(studentMap.get(studentId), courseMap.get(courseId), semester, grade);
                enrollments.add(enrollment);
            });
            res.put("students", students);
            res.put("courses", courses);
            res.put("enrollments", enrollments);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return res;
    }

    public static void writeToJson(Map<String, Object> map, String key, String fileName){
        Map<String, Object> config = new HashMap<>();
        config.put(JsonGenerator.PRETTY_PRINTING, true);
        JsonWriterFactory jsonWriterFactory = Json.createWriterFactory(config);

        try(JsonWriter writer = jsonWriterFactory.createWriter(new FileWriter(fileName))){
            JsonObjectBuilder dataJsonObjectBuilder = Json.createObjectBuilder();
            if(key.equals("students")){
                List<Student> students = (List<Student>) map.get("students");
                JsonArrayBuilder studentJsonArray = Json.createArrayBuilder();
                students.forEach(x -> {
                    JsonObjectBuilder studentJsonObject = Json.createObjectBuilder()
                            .add("studentId", x.getStudentId())
                            .add("name", x.getName());
                    studentJsonArray.add(studentJsonObject);
                });
                dataJsonObjectBuilder.add("students", studentJsonArray);
            }

            if (key.equals("courses")){
                List<Course> courses = (List<Course>) map.get("courses");
                JsonArrayBuilder courseJsonArray = Json.createArrayBuilder();
                courses.forEach(x -> {
                    JsonObjectBuilder courseJsonObject = Json.createObjectBuilder()
                            .add("courseId", x.getCourseId())
                            .add("courseName", x.getCourseName());
                    courseJsonArray.add(courseJsonObject);
                });
                dataJsonObjectBuilder.add("courses", courseJsonArray);
            }

            if (key.equals("enrollments")){
                List<Enrollment> enrollments = (List<Enrollment>) map.get("enrollments");
                JsonArrayBuilder enrollmentsJsonArray = Json.createArrayBuilder();
                enrollments.forEach(x -> {
                    JsonObjectBuilder enrollmentJsonObject = Json.createObjectBuilder()
                            .add("studentId", x.getStudentId().getStudentId())
                            .add("name", x.getCourseId().getCourseId())
                            .add("semester", x.getSemester())
                            .add("grade", x.getGrade());
                    enrollmentsJsonArray.add(enrollmentJsonObject);
                });
                dataJsonObjectBuilder.add("enrollments", enrollmentsJsonArray);
            }
            writer.writeObject(dataJsonObjectBuilder.build());
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
