package core.entity;

import core.domain.Status;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "Appointments")
public class Appointment {

    @ManyToOne
    @JoinColumn(name = "doctorId")
    @ToString.Exclude
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "patientId")
    @ToString.Exclude
    private Patient patient;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Id
    @EqualsAndHashCode.Include
    private LocalDateTime appointmentTime;
}
