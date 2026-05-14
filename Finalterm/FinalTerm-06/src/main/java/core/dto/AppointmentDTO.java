package core.dto;

import core.domain.Status;
import core.entity.Doctor;
import core.entity.Patient;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentDTO implements Serializable {

    private String doctorId;
    private String doctorName;
    private String patientId;
    private String patientName;
    private LocalDateTime appointmentTime;
    private Status status;

    public AppointmentDTO(String doctorId, String patientId, LocalDateTime appointmentTime, Status status) {
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.appointmentTime = appointmentTime;
        this.status = status;
    }
}
