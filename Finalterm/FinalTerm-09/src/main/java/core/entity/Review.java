package core.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "Reviews")
public class Review {

    @EmbeddedId
    @EqualsAndHashCode.Include
    private ReviewId id;

    private int rating;

    private String comment;

    @ManyToOne
    @ToString.Exclude
    @JoinColumn(name = "ISBN")
    @MapsId("ISBN")
    private Book book;

    @ManyToOne
    @ToString.Exclude
    @JoinColumn(name = "person_id")
    @MapsId("person_id")
    private Person person;
}
