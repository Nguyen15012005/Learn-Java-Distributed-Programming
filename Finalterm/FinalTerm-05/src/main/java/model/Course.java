package model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public abstract class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "CourseID")
    protected int id;

    @Column(name = "Credits")
    protected int credit;

    @Column(name = "Title")
    protected String title;

    @ManyToOne
    @JoinColumn(name = "DepartmentID")
    @ToString.Exclude
    private Department department;

    @ManyToMany
    @JoinTable(
        name = "CourseInstructor",
        joinColumns = @JoinColumn(name = "CourseID"),
        inverseJoinColumns = @JoinColumn(name = "PersonID")

    )
    @ToString.Exclude
    private Set<Instructor> instructors = new HashSet<>();

    @OneToMany(mappedBy = "course")
    @ToString.Exclude
    private Set<StudentGrade> studentGrades = new HashSet<>();

}
