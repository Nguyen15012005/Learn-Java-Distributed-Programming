package model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
public class OnsiteCourse extends Course{

    @Column(name = "Days")
    private String days;

    @Column(name = "Location")
    private String location;

    @Column(name = "Time")
    private LocalTime time;
}
