package model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

/**
 * Author : TrungNguyen
 * Date   : 4/7/2026
 * Description:
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "projects")
public class Project {
    @Id
    @Column(name = "project_id", columnDefinition = "VARCHAR(50)")
    @EqualsAndHashCode.Include
    private String id;

    @Column(columnDefinition = "FLOAT", nullable = false)
    private double budget;

    @Column(name = "project_name")
    private String name;

    @ManyToMany
    @JoinTable(
            name = "staff_projects",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "staff_id")
    )
    @ToString.Exclude
    private Set<Staff> staffs = new HashSet<>();
}
