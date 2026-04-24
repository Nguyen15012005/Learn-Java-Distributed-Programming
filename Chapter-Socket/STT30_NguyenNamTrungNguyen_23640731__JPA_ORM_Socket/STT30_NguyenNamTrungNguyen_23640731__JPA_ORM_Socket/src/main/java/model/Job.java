package model;

import domain.JobStatus;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "jobs")
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString(exclude = {"company","skills","applications"})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Job implements Serializable {

    @Id
    @Column(name = "job_id",columnDefinition = "VARCHAR(50)")
    @EqualsAndHashCode.Include
    private String id;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(columnDefinition = "DOUBLE")
    private double salary;

    @Enumerated(EnumType.STRING)
    private JobStatus status;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @ManyToMany
    @JoinTable(name = "jobs_skills",
            joinColumns = @JoinColumn(name = "job_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id")
    )
    private Set<Skill> skills = new HashSet<>();

    @OneToMany(mappedBy = "job")
    private Set<Application> applications = new HashSet<>();
}
