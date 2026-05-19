package core.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "Publishers")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Publisher {

    @Id
    @EqualsAndHashCode.Include
    @Column(name = "publisher_id")
    private String id;

    private String name;

    private String address;

    private String email;

    private String phone;

    @OneToMany(mappedBy = "publisher")
    @ToString.Exclude
    private Set<Book> books = new HashSet<>();
}
