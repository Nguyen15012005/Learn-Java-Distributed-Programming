package model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class StudentGrade implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EnrollmentID")
    @EqualsAndHashCode.Include
    private int enrollmentID;

    @Column(name = "Grade")
    private double grade;

    @ManyToOne()
    @ToString.Exclude
    @JoinColumn(name = "CourseID")
    private Course course;

    @ManyToOne()
    @ToString.Exclude
    @JoinColumn(name = "StudentID")
    private Student student;
}
