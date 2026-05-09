package model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * @author TrungNguyen
 * @created 4/22/2026
 * @description
 */

@Entity
public class Student extends Person{

    @Column(name = "EnrollmentDate")
    private LocalDateTime enrollmentDate;

    @OneToMany(mappedBy = "student")
    private Set<StudentGrade> studentGrades = new HashSet<>();
}
