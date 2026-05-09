package iuh.fit.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Getter
@Setter
public class Enrollment {
    private Student studentId;
    private Course courseId;
    private String semester;
    private double grade;
}
