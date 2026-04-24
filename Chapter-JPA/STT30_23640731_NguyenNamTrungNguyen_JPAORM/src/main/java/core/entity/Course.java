package core.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"instructors", "prerequisites", "enrollments", "department"})
@SuperBuilder


@Entity
@Table(name = "Courses")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Course {
    @Id
    @Column(name = "courseId")
    protected String id;
    protected String title;
    protected Integer credits;

    @ManyToMany(mappedBy = "courses")
    private Set<Instructor> instructors;

    @ManyToMany(mappedBy = "prerequisites")
    private Set<Course> courses;

    @ManyToMany
    @JoinTable(name = "CoursesPrerequisites",
            joinColumns = @JoinColumn(name = "courseId"),
            inverseJoinColumns = @JoinColumn(name = "prerequisiteId")
    )
    private Set<Course> prerequisites;

    @OneToMany(mappedBy = "course")
    private Set<Enrollment> enrollments;

    @ManyToOne
    @JoinColumn(name ="departmentId" )
    private Department department;

}

