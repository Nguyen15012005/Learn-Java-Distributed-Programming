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
@Table(name = "Candidates")
public class Candidate implements Serializable {
    @Id
    @EqualsAndHashCode.Include
    @Column(name = "cand_id",columnDefinition = "VARCHAR(50)")
    private String id;

    private String name;

    @Column(unique = true)
    private String email;

    private int experience;

    @OneToMany(mappedBy = "candidate")
    @ToString.Exclude
    private Set<Application> applications;

    @ManyToMany
    @JoinTable(
            name = "cadidates_skills",
            joinColumns = @JoinColumn(name = "cand_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id")
    )
    @ToString.Exclude
    private Set<Skill> skills;

}
