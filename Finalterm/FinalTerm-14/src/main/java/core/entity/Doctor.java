package core.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@Table(name = "Doctors")
@Entity
@ToString(callSuper = true)
@PrimaryKeyJoinColumn(name = "doctorId")
public class Doctor extends Person{

    private String specialty;

    private String hospital;

    @OneToMany(mappedBy = "doctor")
    @ToString.Exclude
    private Set<Appointment> appointments = new HashSet<>();
}
