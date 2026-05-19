package entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
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
    private LocalDate birthDay;

    private String url;

    @ManyToMany(mappedBy = "artists")
    @ToString.Exclude
    private Set<Album> albums = new HashSet<>();


}
