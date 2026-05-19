package core.dto;

import core.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AppointmentDTO implements Serializable {
    private String doctorId;
    private String doctorName;
    private String patientId;
    private String patientName;
    private LocalDateTime timeAppointment;
    private Status status;

    public AppointmentDTO(String doctorId, String patientId, LocalDateTime timeAppointment, Status status) {
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.timeAppointment = timeAppointment;
        this.status = status;
    }
}
