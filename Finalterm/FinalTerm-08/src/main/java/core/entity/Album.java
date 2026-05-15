package core.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "Albums")
@Entity
public class Album {

    @Id
    @Column(name = "album_id")
    @EqualsAndHashCode.Include
    private String id;

    private String title;

    private double price;

    @Column(name = "year_of_release")
    private int yearOfRelease;

    @Column(name = "download_link")
    private String downloadLink;

    @ManyToOne
    @JoinColumn(name = "genre_id")
    @ToString.Exclude
    private Genre genre;

    @ManyToMany
    @ToString.Exclude
    @JoinTable(
            name = "albums_artists",
            joinColumns = @JoinColumn(name = "album_id"),
            inverseJoinColumns = @JoinColumn(name = "artist_id")
    )
    private Set<Artist> artists = new HashSet<>();

    @ManyToMany
    @ToString.Exclude
    @JoinTable(
            name = "albums_songs",
            joinColumns = @JoinColumn(name = "album_id"),
            inverseJoinColumns = @JoinColumn(name = "song_id")
    )
    private Set<Song> songs = new HashSet<>();
}
