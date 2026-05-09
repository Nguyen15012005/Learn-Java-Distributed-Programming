package model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * @author TrungNguyen
 * @created 4/22/2026
 * @description
 */

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
public class Instructor extends Person{

    @Column(name = "HireDate")
    private LocalDateTime hireDate;

    @ToString.Exclude
    @OneToOne(mappedBy = "instructor")
    private OfficeAssignment officeAssignment;

    @ToString.Exclude
    @ManyToMany(mappedBy = "instructors")
    private Set<Course> courses = new HashSet<>();

}
