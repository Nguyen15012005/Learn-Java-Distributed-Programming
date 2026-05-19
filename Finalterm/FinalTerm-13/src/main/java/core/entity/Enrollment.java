package core.entity;

import core.enums.EnrollStatus;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "Enrollments")
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Enrollment {

    @EmbeddedId
    @EqualsAndHashCode.Include
    @AttributeOverride(name = "enrollDate", column = @Column(name = "enroll_date"))
    private EnrollmentId id;

    private Double score;

    @Enumerated(EnumType.STRING)
    private EnrollStatus status;

    @ManyToOne
    @MapsId("courseId")
    @JoinColumn(name = "course_id")
    @ToString.Exclude
    private Course course;

    @ManyToOne
    @MapsId("studentId")
    @JoinColumn(name = "student_id")
    @ToString.Exclude
    private Student student;
}
