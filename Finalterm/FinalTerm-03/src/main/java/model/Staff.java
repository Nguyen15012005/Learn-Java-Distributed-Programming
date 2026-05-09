package model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
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
@Table(name = "staffs")
public class Staff implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "staff_id")
    @EqualsAndHashCode.Include
    private Long id;

    @Column(nullable = false)
    private int age;

    @Column(name = "staff_name", nullable = false, columnDefinition = "NVARCHAR(100)")
    private String name;

    @ElementCollection(fetch = FetchType.EAGER)
    @JoinTable(
            name = "phones",
            joinColumns = @JoinColumn(name = "staff_id"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"staff_id", "number"})
    )
    @Column(name = "number")
    private Set<String> phoneNumbers;

    @Column(name = "refers")
    private String references;

    @OneToOne(mappedBy = "staff")
    @ToString.Exclude
    private Profile profile;

    @ManyToOne
    @JoinColumn(name = "dept_id")
    @ToString.Exclude
    private Department department;

    @ManyToMany(mappedBy = "staffs")
    @ToString.Exclude
    private Set<Project> projects = new HashSet<>();
}
