package dto;

import entity.DoseStatus;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString

public class RecordDTO implements Serializable {
    private Long recordId;
    private String personId;
    private String vaccineId;
    private int doseNumber;
    private LocalDate injectionDate;
    private DoseStatus status;
}
