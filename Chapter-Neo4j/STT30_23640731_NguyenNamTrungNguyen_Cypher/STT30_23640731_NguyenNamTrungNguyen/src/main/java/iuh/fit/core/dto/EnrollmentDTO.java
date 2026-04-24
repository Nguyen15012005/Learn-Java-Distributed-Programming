
package iuh.fit.core.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import iuh.fit.core.entity.Course;
import iuh.fit.core.entity.Student;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class EnrollmentDTO {
    @JsonProperty("student_id")
    private String studentId;
    @JsonProperty("course_id")
    private String courseId;
    private String semester;
    private int grade;
}

    