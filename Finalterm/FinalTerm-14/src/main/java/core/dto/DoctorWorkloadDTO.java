package core.dto;

import core.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorWorkloadDTO implements Serializable {

    private String doctorId;

    private String doctorName;

    private Date appointmentTime;

    private Long count;
}
