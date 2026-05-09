package iuh.fit.util;

import iuh.fit.model.Address;
import iuh.fit.model.ClassInfo;
import iuh.fit.model.Student;

import javax.json.*;
import javax.json.stream.JsonGenerator;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;

public class JsonUtils{
    public static List<ClassInfo> toJson(String fileName){
        List<ClassInfo> res = new ArrayList<>();

        try(JsonReader reader = Json.createReader(new FileReader(fileName))){
            JsonArray classInfoJsonArray = reader.readArray();
            classInfoJsonArray.forEach(classInfoJsonValue -> {
                JsonObject classInfoJsonObject = classInfoJsonValue.asJsonObject();
                String name = classInfoJsonObject.getString("name");
                String teacher = classInfoJsonObject.getString("teacher");
                int room = classInfoJsonObject.getInt("room");
                String start_time = classInfoJsonObject.getString("start_time");
                String end_time = classInfoJsonObject.getString("end_time");

                JsonArray studentJsonArray = classInfoJsonObject.getJsonArray("students");
                List<Student> students = new ArrayList<>();
                studentJsonArray.forEach(studentJsonValue -> {
                    JsonObject studentJsonObject = studentJsonValue.asJsonObject();
                    String nameStudent = studentJsonObject.getString("name");
                    int age = studentJsonObject.getInt("age");
                    double gpa = studentJsonObject.getJsonNumber("gpa").doubleValue();

                    JsonObject addressJsonObject = studentJsonObject.getJsonObject("address");
                    Address address = new Address(
                            addressJsonObject.getString("street"),
                            addressJsonObject.getString("city"),
                            addressJsonObject.getString("state"),
                            addressJsonObject.getString("zip")
                    );

                    Student student = new Student(nameStudent, age, gpa, address);
                    students.add(student);

                });
                ClassInfo classInfo = new ClassInfo(name, teacher, room, start_time, end_time, students);
                res.add(classInfo);
            });
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return res;
    }

    public static void writeJsonToFile(List<ClassInfo> classInfor, String fileName){
//        Làm đẹp dữ liệu khi in ra
        Map<String, Object> config = new HashMap<>();
        config.put(JsonGenerator.PRETTY_PRINTING, true);
        JsonWriterFactory jsonWriterFactory = Json.createWriterFactory(config);

        try(JsonWriter writer = jsonWriterFactory.createWriter(new FileWriter(fileName))){
            JsonArrayBuilder classInfoJsonArray = Json.createArrayBuilder();
            classInfor.forEach(x -> {
                JsonObjectBuilder classInfoJsonObject = Json.createObjectBuilder()
                        .add("name", x.getName())
                        .add("teacher", x.getTeacher())
                        .add("room", x.getRoom())
                        .add("start_time", x.getStart_time())
                        .add("end_time", x.getEnd_time());
                    JsonArrayBuilder studentJsonArray = Json.createArrayBuilder();
                    x.getStudents().forEach(y -> {
                        JsonObjectBuilder studentJsonObject = Json.createObjectBuilder()
                                .add("name", y.getName())
                                .add("age", y.getAge())
                                .add("gpa", y.getGpa());

                            JsonObjectBuilder addressJsonObject = Json.createObjectBuilder()
                                    .add("street", y.getAddress().getStreet())
                                    .add("city", y.getAddress().getCity())
                                    .add("state", y.getAddress().getState())
                                    .add("zip", y.getAddress().getZip());

                            studentJsonObject.add("address",addressJsonObject);
                            studentJsonArray.add(studentJsonObject);
                    }
                    
                    );
                            classInfoJsonObject.add("students", studentJsonArray);
                            classInfoJsonArray.add(classInfoJsonObject);


            });
                    writer.writeArray(classInfoJsonArray.build());
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

}