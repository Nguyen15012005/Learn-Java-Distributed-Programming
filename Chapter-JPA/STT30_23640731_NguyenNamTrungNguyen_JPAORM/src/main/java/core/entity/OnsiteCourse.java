package core.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@SuperBuilder

@Entity
@Table(name = "OnsiteCourses")
public class OnsiteCourse extends Course {
    private String days;
    private String location;
    private LocalDateTime time;
}