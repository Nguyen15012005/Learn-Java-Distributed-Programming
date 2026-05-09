package model;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * @author TrungNguyen
 * @created 4/22/2026
 * @description
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class OfficeAssignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id;

    private String location;

    private LocalDateTime timestamp;

    @OneToOne
    @JoinColumn(name = "InstructorID")
    @ToString.Exclude
    @MapsId
    private Instructor instructor;
}
