package core.entity;

import core.domain.JobStatus;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@Entity
@Table(name = "jobs")
public class Job implements Serializable {
    @Id
    @Column(name = "job_id", columnDefinition = "VARCHAR(50)")
    @EqualsAndHashCode.Include
    private String id;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    private double salary;

    @Enumerated(EnumType.STRING)
    private JobStatus status;

    @ManyToOne
    @ToString.Exclude
    @JoinColumn(name = "company_id")
    private Company company;

    @ManyToMany
    @JoinTable(
            name = "jobs_skills",
            joinColumns = @JoinColumn(name = "job_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id")
    )
    @ToString.Exclude
    private Set<Skill> skills;

    @OneToMany(mappedBy = "job")
    private Set<Application> applications;
}
