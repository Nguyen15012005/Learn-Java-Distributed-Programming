package core.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "Books")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Inheritance(strategy = InheritanceType.JOINED)
public class Book {

    @Id
    @EqualsAndHashCode.Include
    @Column(name = "ISBN")
    protected String ISBN;

    protected String name;

    @Column(name = "publish_year")
    protected int publishYear;

    @Column(name = "num_of_pages")
    protected int numOfPage;

    protected double price;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "books_authors",
            joinColumns = @JoinColumn(name = "ISBN"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"ISBN", "author"})
    )
    @ToString.Exclude
    @Column(name = "author")
    protected Set<String> authors;

    @OneToMany(mappedBy = "book")
    @ToString.Exclude
    private Set<Review> reviews = new HashSet<>();

    @ManyToOne
    @ToString.Exclude
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;

}
