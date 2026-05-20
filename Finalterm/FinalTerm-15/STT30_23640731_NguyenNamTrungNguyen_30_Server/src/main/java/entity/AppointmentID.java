package entity;


import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Embeddable
public class AppointmentID {
    private String doctorId;

    private String patientId;

    private LocalDateTime appointmentTime;

}
