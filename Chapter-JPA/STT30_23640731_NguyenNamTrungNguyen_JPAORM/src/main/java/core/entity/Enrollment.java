package core.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder

@Entity
@IdClass(Enrollment.EnrollmentId.class)
@Table(name = "Enrollments")
public class Enrollment {
    @Id
    @ManyToOne
    @JoinColumn(name = "studentId")
    private Student student;
    @Id
    @ManyToOne
    @JoinColumn(name = "courseId")
    private Course course;
    @Id
    private String semester;
    private Double grade;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    @Builder
    @EqualsAndHashCode
    public static class EnrollmentId implements Serializable {
        private String student;
        private String course;
        private String semester;
    }
}

//compound key - composite key