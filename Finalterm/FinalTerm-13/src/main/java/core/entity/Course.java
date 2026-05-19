package core.entity;

import core.enums.CourseStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "Courses")
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Course {

    @Id
    @EqualsAndHashCode.Include
    @Column(name = "course_id")
    private String id;

    private String name;

    private String description;

    @Column(name = "tuition_fee")
    private double tuitionFee;

    @Enumerated(EnumType.STRING)
    private CourseStatus status;

    @ManyToOne
    @JoinColumn(name = "genre_id")
    @ToString.Exclude
    private Genre genre;

    @ManyToOne
    @JoinColumn(name = "instructor_id")
    @ToString.Exclude
    private Instructor instructor;

    @OneToMany(mappedBy = "course")
    @ToString.Exclude
    private Set<Enrollment> enrollments = new HashSet<>();
}
