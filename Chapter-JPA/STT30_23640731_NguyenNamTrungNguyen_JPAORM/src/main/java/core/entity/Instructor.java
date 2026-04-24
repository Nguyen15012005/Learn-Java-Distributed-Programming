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
@ToString(callSuper = true, exclude = {"officeAssignment", "courses"})
@SuperBuilder

@Entity
@DiscriminatorValue("INSTRUCTOR")
public class Instructor extends Person {
    private LocalDateTime hireDate;

    @OneToOne(mappedBy = "instructor", cascade = {CascadeType.REMOVE, CascadeType.MERGE}, orphanRemoval = true)
    private OfficeAssignment officeAssignment;


    @ManyToMany
        @JoinTable(name = "CoursesInstructors",
            joinColumns = @JoinColumn(name = "instructorId"),
            inverseJoinColumns = @JoinColumn(name = "courseId")
    )
    private Set<Course> courses;
}