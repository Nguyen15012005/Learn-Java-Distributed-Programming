package core.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@Table(name = "Patients")
@Entity
@ToString(callSuper = true)
@PrimaryKeyJoinColumn(name = "patientId")
public class Patient extends Person{

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "Phones",
            joinColumns = @JoinColumn(name = "patientId")
    )
    @Column(name = "phoneNumber")
    private Set<String> phones = new HashSet<>();

    private String address;

    @OneToMany(mappedBy = "patient")
    @ToString.Exclude
    private Set<Appointment> appointments = new HashSet<>();
}
