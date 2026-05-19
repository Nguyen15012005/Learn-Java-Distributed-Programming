package core.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Data
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "People")
public abstract class Person {

    @Id
    @EqualsAndHashCode.Include
    @Column(name = "personId")
    protected String id;

    protected String fullName;

    protected String email;
}
