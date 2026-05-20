package core.entity;

import core.enums.Status;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "Appointments")
@Entity
public class Appointment{

    @EmbeddedId
    private AppointmentID id;

    @Enumerated(EnumType.STRING)
    private Status status;


    @ManyToOne
    @MapsId("doctorId")
    @JoinColumn(name = "doctorId")
    @ToString.Exclude
    private Doctor doctor;

    @ManyToOne
    @MapsId("patientId")
    @JoinColumn(name = "patientId")
    @ToString.Exclude
    private Patient patient;
}
