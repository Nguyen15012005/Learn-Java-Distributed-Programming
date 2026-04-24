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
@Table(name = "skills")
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString(exclude = {"jobs","candidates"})
public class Skill implements Serializable {

    @Id
    @Column(name = "skill_id",columnDefinition = "VARCHAR(50)")
    private String id;

    private String name;

    @ManyToMany(mappedBy = "skills")
    private Set<Candidate> candidates = new HashSet<>();

    @ManyToMany(mappedBy = "skills")
    private Set<Job> jobs = new HashSet<>();
}
