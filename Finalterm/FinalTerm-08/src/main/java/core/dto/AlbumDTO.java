package core.dto;

import core.entity.Artist;
import core.entity.Genre;
import core.entity.Song;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AlbumDTO implements Serializable {

    private String id;

    private String title;

    private double price;

    private int yearOfRelease;

    private String downloadLink;

    private String genreId;

}
