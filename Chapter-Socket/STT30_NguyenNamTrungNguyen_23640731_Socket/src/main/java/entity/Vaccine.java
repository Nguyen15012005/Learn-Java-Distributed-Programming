package entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "records")

@JsonIgnoreProperties({"records"})

@Entity
@Table(name = "vaccines")

public class Vaccine {
    @Id
    @Column(name = "vaccine_id")
    private String vaccineId;

    @Column(name = "vaccine_name")
    private String vaccineName;

    private String manufacturer;

    @Column(name = "expiration_date")
    private LocalDate expirationDate;

    @JsonIgnore
    @OneToMany(mappedBy = "vaccine")
    private List<Record> records;
}