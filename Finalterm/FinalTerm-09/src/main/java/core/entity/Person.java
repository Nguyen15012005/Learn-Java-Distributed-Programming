package core.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "People")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Person {

    @Id
    @EqualsAndHashCode.Include
    @Column(name = "person_id")
    private String id;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "first_name")
    private String firstName;

    private String email;

    @Column(name = "professional_title")
    private String professionalTitle;

    @OneToMany(mappedBy = "person")
    @ToString.Exclude
    private Set<Review> reviews = new HashSet<>();
    
}
