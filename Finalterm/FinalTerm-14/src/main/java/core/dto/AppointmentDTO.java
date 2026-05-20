package core.dto;

import core.enums.Status;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AppointmentDTO implements Serializable {

    private String doctorId;

    private String doctorName;

    private String patientId;

    private String patientName;

    private LocalDateTime appointmentTime;

    private Status status;

    public AppointmentDTO(LocalDateTime appointmentTime, Status status, String doctorId, String patientId) {
        this.appointmentTime = appointmentTime;
        this.status = status;
        this.doctorId = doctorId;
        this.patientId = patientId;
    }
}
