package core.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"instructor"})
@Builder

@Entity
@Table(name="OfficeAssignments")
public class OfficeAssignment {

    @Id
    private String id;
    private String location;
    private LocalDateTime timestamp;

    @OneToOne
    @MapsId
    @JoinColumn(name="instructorId", unique = true)
    private Instructor instructor;
}