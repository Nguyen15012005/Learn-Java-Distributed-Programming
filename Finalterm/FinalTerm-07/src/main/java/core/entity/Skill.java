package core.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@Entity
@Table(name = "skills")
public class Skill implements Serializable {

    @Id
    @Column(name = "skill_id", columnDefinition = "VARCHAR(50)")
    @EqualsAndHashCode.Include
    private String id;

    private String name;

    @ManyToMany(mappedBy = "skills")
    @ToString.Exclude
    private Set<Candidate> candidates;

    @ManyToMany(mappedBy = "skills")
    @ToString.Exclude
    private Set<Job> jobs;
}
