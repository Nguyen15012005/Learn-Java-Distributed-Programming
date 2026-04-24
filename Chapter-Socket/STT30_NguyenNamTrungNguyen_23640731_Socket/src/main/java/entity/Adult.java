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
@Table(name = "adults")

public class Adult extends Person {
    @Column(name = "identity_number")
    private String identityNumber;

    private String occupation;

    @JsonProperty("personType")
    public String getPersonType() {
        return "ADULT";
    }

}
