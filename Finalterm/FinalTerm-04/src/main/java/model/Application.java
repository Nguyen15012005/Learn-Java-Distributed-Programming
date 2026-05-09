package model;

import domain.AppStatus;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "applications")
public class Application {

    @Column(name = "applied_date")
    private LocalDate appliedDate;

    @Enumerated(value = EnumType.STRING)
    private AppStatus status;

    @ManyToOne
    @Id
    @JoinColumn(name = "cand_id")
    @ToString.Exclude
    private Candidate candidate;

    @ManyToOne
    @Id
    @JoinColumn(name = "job_id")
    @ToString.Exclude
    private Job job;

}
