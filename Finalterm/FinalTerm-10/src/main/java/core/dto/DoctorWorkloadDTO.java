package core.dto;

import core.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DoctorWorkloadDTO implements Serializable {
    private String doctorId;
    private String doctorName;
    private LocalDate timeAppointment;
    private Long count;
}
