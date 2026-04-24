package iuh.fit.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@NodeEntity(label = "Student")
public class Student {
    @Id
    @Property("student_id")
    private String id;
    private String name;
    private double gpa;
}