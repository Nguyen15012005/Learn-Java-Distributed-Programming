package model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "companies")
public class Company {

    @Id
    @Column(name = "company_id", columnDefinition = "VARCHAR(50)")
    private String id;

    private String name;

    private String industry;

    @OneToMany(mappedBy = "company")
    @ToString.Exclude
    private Set<Job> jobs = new HashSet<>();
}
