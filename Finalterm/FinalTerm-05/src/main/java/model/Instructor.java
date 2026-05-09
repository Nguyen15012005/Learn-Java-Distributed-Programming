package model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;

import java.time.LocalDateTime;

/**
 * @author TrungNguyen
 * @created 4/22/2026
 * @description
 */

@Entity
public class Instructor extends Person{

    @Column(name = "HireDate")
    private LocalDateTime hireDate;

    @OneToOne(mappedBy = "instructor")
    private OfficeAssignment officeAssignment;

}
