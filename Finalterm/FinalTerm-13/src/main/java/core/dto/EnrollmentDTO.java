package core.dto;

import core.entity.Course;
import core.entity.EnrollmentId;
import core.entity.Student;
import core.enums.EnrollStatus;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EnrollmentDTO implements Serializable {

    private Date enroll_date;

    private Double score;

    private EnrollStatus status;

    private String courseId;

    private String studentId;
}
