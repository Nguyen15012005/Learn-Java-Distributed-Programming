
package iuh.fit.entity;

import lombok.*;
import org.neo4j.ogm.annotation.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString

@RelationshipEntity(type = "ERROOLLED_IN")
public class Enrollment {
    @Id
    @GeneratedValue
    private Long enrollmentId;
    @StartNode
    private Student student;
    @EndNode
    private Course course;
    private String semester;
    private double grade;
}

    