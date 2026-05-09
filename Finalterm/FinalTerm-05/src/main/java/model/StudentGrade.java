package model;

import jakarta.persistence.*;

@Entity
public class StudentGrade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EnrollmentID")
    private int enrollmentID;

    @Column(name = "Grade")
    private double grade;

    @ManyToOne()
    @JoinColumn(name = "CourseID")
    private Course course;

    @ManyToOne()
    @JoinColumn(name = "StudentID")
    private Student student;
}
