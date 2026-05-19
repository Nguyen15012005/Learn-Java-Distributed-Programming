package entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
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
    @ToString.Exclude
    @JoinColumn(name = "genre_id")
    private Genre genre;

    @ManyToMany
    @JoinTable(
            name = "albums_artists",
            joinColumns = @JoinColumn(name = "album_id"),
            inverseJoinColumns = @JoinColumn(name = "artist_id")
    )
    @ToString.Exclude
    private Set<Artist> artists = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "albums_songs",
            joinColumns = @JoinColumn(name = "album_id"),
            inverseJoinColumns = @JoinColumn(name = "song_id")
    )
    @ToString.Exclude
    private Set<Song> songs = new HashSet<>();

}
