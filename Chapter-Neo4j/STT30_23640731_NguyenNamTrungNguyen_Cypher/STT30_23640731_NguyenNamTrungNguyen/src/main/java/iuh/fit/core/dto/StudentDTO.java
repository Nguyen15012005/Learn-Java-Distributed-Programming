package iuh.fit.core.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class StudentDTO {
    @JsonProperty("student_id")
    private String id;
    private String name;
    private double gpa;
}