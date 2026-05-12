package model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * @author TrungNguyen
 * @created 4/22/2026
 * @description
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@Entity
@ToString(callSuper = true)
public class Student extends Person implements Serializable {

    @ToString.Exclude
    @Column(name = "EnrollmentDate")
    private LocalDateTime enrollmentDate;

    @ToString.Exclude
    @OneToMany(mappedBy = "student")
    private Set<StudentGrade> studentGrades = new HashSet<>();
}
