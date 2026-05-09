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

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "departments")
public class Department {
    @Id
    @Column(name = "dept_id", columnDefinition = "VARCHAR(50)")
    @EqualsAndHashCode.Include
    private String id;

    private String location;

    @Column(name = "dept_name")
    private String name;

    @OneToMany(mappedBy = "department")
    @ToString.Exclude
    private Set<Staff> staffs = new HashSet<>();
}
