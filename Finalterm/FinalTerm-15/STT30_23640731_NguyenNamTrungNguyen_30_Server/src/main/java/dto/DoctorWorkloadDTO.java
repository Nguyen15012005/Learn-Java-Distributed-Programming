package dto;

import lombok.*;

import java.io.Serializable;
import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DoctorWorkloadDTO implements Serializable {

    private String id;

    private String fullName;

    private Date appointmentTime;

    private Long count;
}
