package entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString(exclude = "records")

@JsonIgnoreProperties({"records"})

@Entity
@Table(name = "persons")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Person {
    @Id
    @Column(name = "person_id")
    protected String personId;

    @Column(name = "full_name")
    protected String fullName;

    protected String gender;
    protected LocalDate dob;
    protected double height;
    protected double weight;

    @JsonIgnore
    @OneToMany(mappedBy = "person")
    private List<Record> records;

}