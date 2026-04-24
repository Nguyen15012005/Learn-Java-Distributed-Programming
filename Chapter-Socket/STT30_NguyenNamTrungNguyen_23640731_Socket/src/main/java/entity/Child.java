package entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString(callSuper = true)

@Entity
@Table(name = "children")
public class Child extends Person {
    @Column(name = "guardian_name")
    private String guardianName;

    @JsonProperty("personType")
    public String getPersonType() {
        return "CHILD";
    }
}