package iuh.fit.model;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Data
public class ClassInfo {
    private String name;
    private String teacher;
    private int room;
    private String start_time;
    private String end_time;

    private List<Student> students;

}
