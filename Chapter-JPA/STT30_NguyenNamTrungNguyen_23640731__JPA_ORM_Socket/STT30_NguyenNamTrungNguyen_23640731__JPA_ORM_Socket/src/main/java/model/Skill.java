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
@Table(name = "skills")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Skill implements Serializable {

    @Id
    @Column(name = "skill_id", columnDefinition = "VARCHAR(50)")
    @EqualsAndHashCode.Include
    private String id;

    private String name;

    @ManyToMany(mappedBy = "skills")
    @ToString.Exclude
    private Set<Job> jobs = new HashSet<>();

    @ManyToMany(mappedBy = "skills")
    @ToString.Exclude
    private Set<Candidate> candidates = new HashSet<>();
}
