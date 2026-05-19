package core.entity;

import core.enums.Level;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "Students")
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Student {

    @Id
    @EqualsAndHashCode.Include
    @Column(name = "student_id")
    private String id;

    @Column(name = "full_name")
    private String fullName;

    private String email;

    @Column(name = "birth_date")
    private LocalDate birthDay;

    private String address;

    @Enumerated(EnumType.STRING)
    private Level level;

    @OneToMany(mappedBy = "student")
    @ToString.Exclude
    private Set<Enrollment> enrollments = new HashSet<>();

}
