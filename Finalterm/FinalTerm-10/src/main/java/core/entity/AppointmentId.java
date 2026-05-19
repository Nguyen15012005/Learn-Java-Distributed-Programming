package core.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Embeddable
public class AppointmentId {

    private String patientId;

    private String doctorId;

    private LocalDateTime appointmentTime;
}
