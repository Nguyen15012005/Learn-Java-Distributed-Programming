package model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Department implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DepartmentID")
    @EqualsAndHashCode.Include
    private int id;

    @Column(name = "Administrator")
    private int administrator;

    @Column(name = "Budget")
    private double budget;

    @Column(name = "Name")
    private String name;

    @Column(name = "startDate")
    private LocalDateTime startDate;

    @ToString.Exclude
    @OneToMany(mappedBy = "department")
    private Set<Course> courses = new HashSet<>();
}
