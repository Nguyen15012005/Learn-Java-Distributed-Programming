package iuh.fit.core.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import iuh.fit.core.entity.Department;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CourseDTO {
    @JsonProperty("course_id")
    private String id;
    private String name;
    private int hours;
    private String deptName;
}