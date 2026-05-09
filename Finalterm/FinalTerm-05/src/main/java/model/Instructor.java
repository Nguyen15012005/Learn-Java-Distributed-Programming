package model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * @author TrungNguyen
 * @created 4/22/2026
 * @description
 */

@Entity
public class Instructor extends Person{

    @Column(name = "HireDate")
    private LocalDateTime hireDate;

    @OneToOne(mappedBy = "instructor")
    private OfficeAssignment officeAssignment;

    @ManyToMany(mappedBy = "instructors")
    private Set<Course> courses = new HashSet<>();

}
