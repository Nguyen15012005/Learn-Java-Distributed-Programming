package core.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "Instructors")
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Instructor {

    @Id
    @EqualsAndHashCode.Include
    @Column(name = "instructor_id")
    private String id;

    @Column(name = "full_name")
    private String fullName;

    private String email;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "instructor_phones",
            joinColumns = @JoinColumn(name = "instructor_id")
    )
    @Column(name = "phone_number")
    private Set<String> phones = new HashSet<>();

    private String major;

    private String degree;

    @OneToMany(mappedBy = "instructor")
    @ToString.Exclude
    private Set<Course> courses = new HashSet<>();
}
