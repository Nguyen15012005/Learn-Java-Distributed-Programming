package model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import java.time.LocalDateTime;

/**
 * @author TrungNguyen
 * @created 4/22/2026
 * @description
 */

@Entity
public class Student extends Person{

    @Column(name = "EnrollmentDate")
    private LocalDateTime enrollmentDate;
}
