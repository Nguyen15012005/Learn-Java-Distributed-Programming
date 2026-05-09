package model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "candidates")
public class Candidate {

    @Id
    @Column(name = "cand_id", columnDefinition = "VARCHAR(50)")
    private String id;

    private String name;

    @Column(unique = true)
    private String email;

    private int experience;

    @ManyToMany
    @JoinTable(
            name = "candidates_skills",
            joinColumns = @JoinColumn(name = "cand_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id")
    )
    @ToString.Exclude
    private Set<Skill> skills = new HashSet<>();

    @OneToMany(mappedBy = "candidate")
    @ToString.Exclude
    private Set<Application> applications = new HashSet<>();
}
