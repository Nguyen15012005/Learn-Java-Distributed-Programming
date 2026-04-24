package iuh.fit.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.neo4j.ogm.annotation.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString

@NodeEntity(label = "Course")
public class Course {
    @Id
    @Property("course_id")
    private String id;
    private String name;
    private int hours;

    @Relationship(type = "BELONGS_TO", direction = Relationship.Direction.OUTGOING)
    private Department department;
}