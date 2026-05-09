package model;

import domain.JobStatus;
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
@Table(name = "jobs")
public class Job {

    @Id
    @Column(name = "job_id", columnDefinition = "VARCHAR(50)")
    private String id;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    private double salary;

    @Enumerated(value = EnumType.STRING)
    private JobStatus status;

    @ManyToOne
    @JoinColumn(name = "company_id")
    @ToString.Exclude
    private Company company;

    @ManyToMany
    @JoinTable(
            name = "jobs_skills",
            joinColumns = @JoinColumn(name = "job_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id")
    )
    @ToString.Exclude
    private Set<Skill> skills = new HashSet<>();

    @OneToMany(mappedBy = "job")
    @ToString.Exclude
    private Set<Application> applications = new HashSet<>();

}
