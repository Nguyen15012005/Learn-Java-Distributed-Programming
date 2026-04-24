package model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "companies")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Company implements Serializable {

    @Id
    @Column(name = "company_id", columnDefinition = "VARCHAR(50)")
    @EqualsAndHashCode.Include
    private String id;

    private String name;

    private String industry;

    @OneToMany(mappedBy = "company")
    @ToString.Exclude
    private Set<Job> jobs = new HashSet<>();
}
