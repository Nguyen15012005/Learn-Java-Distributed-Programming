package core.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@ToString(callSuper = true)
@PrimaryKeyJoinColumn(name = "patientId")
@Entity
@Table(name = "Patients")
public class Patient extends Person{

    @ElementCollection(fetch = FetchType.EAGER)
    @JoinTable(
            name = "Phones",
            joinColumns = @JoinColumn(name = "patientId")
//            uniqueConstraints = @UniqueConstraint(columnNames = {"patientId", "phoneNumber"})
    )
    @ToString.Exclude
    @Column(name = "phoneNumber")
    private Set<String> phones;

    private String address;

    @ToString.Exclude
    @OneToMany(mappedBy = "patient")
    private Set<Appointment> appointments = new HashSet<>();
}
