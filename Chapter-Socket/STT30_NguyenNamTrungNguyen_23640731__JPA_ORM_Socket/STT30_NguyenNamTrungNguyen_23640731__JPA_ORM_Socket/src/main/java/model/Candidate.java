package model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "candidates")
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString(exclude = {"skills","applications"})
public class Candidate implements Serializable {

    @Id
    @Column(name = "cand_id",columnDefinition = "VARCHAR(50)")
    private String id;

    private String name;

    @Column(unique = true)
    private String email;

    private int experience;

    @ManyToMany
    @JoinTable(name = "candidates_skills",
            joinColumns = @JoinColumn(name = "cand_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id")
    )
    private Set<Skill> skills = new HashSet<>();

    @OneToMany(mappedBy = "candidate")
    private Set<Application> applications = new HashSet<>();
}
