package entity;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "Appointments")
@Entity
public class Appointment {

    @EmbeddedId
    private AppointmentID appointmentID;

    @ManyToOne
    @ToString.Exclude
    @JoinColumn(name = "doctorId")
    @MapsId("doctorId")
    private Doctor doctor;

    @ManyToOne
    @ToString.Exclude
    @JoinColumn(name = "patientId")
    @MapsId("patientId")
    private Patient patient;

    @Enumerated(EnumType.STRING)
    private Status status;
}
