package core.entity;

import core.domain.AppStatus;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@Entity
@Table(name = "applications")
public class Application implements Serializable {

    @Column(name = "applied_date", columnDefinition = "DATE")
    private LocalDate appliedDate;

    @Enumerated(EnumType.STRING)
    private AppStatus status;

    @ManyToOne
    @Id
    @MapsId
    @JoinColumn(name = "cand_id")
    @ToString.Exclude
    private Candidate candidate;

    @ManyToOne
    @Id
    @MapsId
    @JoinColumn(name = "job_id")
    @ToString.Exclude
    private Job job;
}
