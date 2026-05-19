package core.entity;


import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Embeddable
public class EnrollmentId  implements Serializable {
    private String studentId;
    private String courseId;
    private Date enrollDate;
}
