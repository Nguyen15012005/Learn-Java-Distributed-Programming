package core.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "Genres")
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Genre {

    @Id
    @EqualsAndHashCode.Include
    @Column(name = "genre_id")
    private String id;

    private String name;

    private String description;

    @OneToMany(mappedBy = "genre")
    @ToString.Exclude
    private Set<Course> courses = new HashSet<>();
}
