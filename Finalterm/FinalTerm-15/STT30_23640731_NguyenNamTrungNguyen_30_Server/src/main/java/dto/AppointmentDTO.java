package dto;

import entity.AppointmentID;
import entity.Doctor;
import entity.Patient;
import entity.Status;
import jakarta.persistence.*;
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

//    Mã số bác sĩ, tên bác sĩ, mã số bệnh nhân, tên bệnh nhân, thời
//    gian khám và trạng thái

    public AppointmentDTO(LocalDateTime appointmentTime, String doctorId, String patientId, Status status) {
        this.appointmentTime = appointmentTime;
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.status = status;
    }
}
