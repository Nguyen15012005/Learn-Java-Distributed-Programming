package core.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "Genres")
@Entity
public class Genre {

    @Id
    @Column(name = "genre_id")
    @EqualsAndHashCode.Include
    private String id;

    private String name;

    private String description;

    @OneToMany(mappedBy = "genre")
    @ToString.Exclude
    private Set<Album> albums = new HashSet<>();

}
