package core.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "People")
public abstract class Person {

    @EqualsAndHashCode.Include
    @Id
    @Column(name = "personId")
    protected String id;
    protected String fullName;
    protected String email;
}
