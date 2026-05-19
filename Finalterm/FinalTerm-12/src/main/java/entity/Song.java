package entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "Songs")
@Entity
public class Song {

    @Id
    @EqualsAndHashCode.Include
    @Column(name = "song_id")
    private String id;

    private String name;

    private String runtime;

    private String lyric;

    @Column(name = "file_link")
    private String fileLink;

    @ManyToMany(mappedBy = "songs")
    @ToString.Exclude
    private Set<Album> albums = new HashSet<>();
}
