package model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "companies")
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString(exclude = {"jobs"})
public class Company implements Serializable {

    @Id
    @Column(name = "company_id",columnDefinition = "VARCHAR(50)")
    private String id;

    private String name;

    private String industry;

    @OneToMany(mappedBy = "company")
    private Set<Job> jobs = new HashSet<>();
}
