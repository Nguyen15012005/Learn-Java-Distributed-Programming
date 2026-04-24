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
@ToString(callSuper = true, exclude = {"enrollments"})
@SuperBuilder

@Entity
@DiscriminatorValue("STUDENT")

@NamedQueries({
        @NamedQuery(
                name = "Student.findByName",
                query = "SELECT s FROM Student s WHERE s.firstName LIKE :firstName"
        )
})

public class Student extends Person {

    private LocalDateTime enrollmentDate;

    @OneToMany(mappedBy = "student")
    private Set<Enrollment> enrollments;
}