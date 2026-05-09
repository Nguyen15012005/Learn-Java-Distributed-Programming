package iuh.fit.model;

import iuh.fit.model.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Author : TrungNguyen
 * Date   : 4/2/2026
 * Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Patient extends Person{
    private Gender gender;
    private String dateOfBirth;
    private String address;
}
