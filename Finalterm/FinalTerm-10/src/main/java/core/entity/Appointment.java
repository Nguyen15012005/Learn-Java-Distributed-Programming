package core.entity;

import core.enums.Status;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Data
@Table(name = "Appointments")
public class Appointment{

    @EmbeddedId
    @EqualsAndHashCode.Include
    private AppointmentId id;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @MapsId("doctorId")
    @ToString.Exclude
    @JoinColumn(name = "doctorId")
    private Doctor doctor;

    @ManyToOne
    @MapsId("patientId")
    @ToString.Exclude
    @JoinColumn(name = "patientId")
    private Patient patient;

}
