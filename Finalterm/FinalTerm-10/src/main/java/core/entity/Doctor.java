package core.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@Entity
@Data
@Table(name = "Doctors")
@PrimaryKeyJoinColumn(name = "doctorId")
@ToString(callSuper = true)
public class Doctor extends Person{

    protected String specialty;

    protected String hospital;

    @OneToMany(mappedBy = "doctor")
    @ToString.Exclude
    private Set<Appointment> appointments = new HashSet<>();
}
