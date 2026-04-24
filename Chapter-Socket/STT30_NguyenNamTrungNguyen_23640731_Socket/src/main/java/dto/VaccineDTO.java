package dto;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString

public class VaccineDTO implements Serializable {
    private String vaccineId;
    private String vaccineName;
    private String manufacturer;
}
