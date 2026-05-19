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
@Table(name = "Patients")
@PrimaryKeyJoinColumn(name = "patientId")
@ToString(callSuper = true)
public class Patient extends Person{

    protected String address;

    @ElementCollection
    @CollectionTable(
            name = "phones",
            joinColumns = @JoinColumn(name = "patientId")

    )
    @Column(name = "phoneNumber")
    @ToString.Exclude
    protected Set<String> phones;

    @OneToMany(mappedBy = "patient")
    @ToString.Exclude
    private Set<Appointment> appointments = new HashSet<>();
}
