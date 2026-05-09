package model;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * @author TrungNguyen
 * @created 4/22/2026
 * @description
 */
@Entity
public class OfficeAssignment {

    @Id
    private int id;

    private String location;

    private LocalDateTime timestamp;

    @OneToOne
    @JoinColumn(name = "InstructorID")
    @MapsId
    private Instructor instructor;
}
