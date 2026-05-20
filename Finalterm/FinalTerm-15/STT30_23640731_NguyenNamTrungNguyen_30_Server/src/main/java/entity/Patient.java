package entity;

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
@PrimaryKeyJoinColumn(name = "patientId")
@ToString(callSuper = true)
public class Patient extends Person{

    private String address;

    @ToString.Exclude
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "Phones",
            joinColumns = @JoinColumn(name = "patientId")
    )
    @Column(name = "phoneNumber")
    private Set<String> phones = new HashSet<>();

    @OneToMany(mappedBy = "patient")
    @ToString.Exclude
    private Set<Appointment> appointments = new HashSet<>();
}