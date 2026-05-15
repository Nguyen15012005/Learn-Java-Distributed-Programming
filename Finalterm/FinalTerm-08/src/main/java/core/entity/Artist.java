package core.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "Artists")
@Entity
public class Artist {

    @Id
    @Column(name = "artist_id")
    @EqualsAndHashCode.Include
    private String id;

    private String name;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    private String url;

    @ManyToMany(mappedBy = "artists")
    @ToString.Exclude
    private Set<Album> albums = new HashSet<>();

}
