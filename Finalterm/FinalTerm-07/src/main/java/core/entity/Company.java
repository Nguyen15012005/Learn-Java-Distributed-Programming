package core.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@Entity
@Table(name = "Company")
public class Company implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    @Column(name = "company_id",columnDefinition = "VARCHAR(50)")
    private String id;

    private String name;

    private String industry;

    @OneToMany(mappedBy = "company")
    @ToString.Exclude
    private Set<Job> jobs;
}
