package iuh.fit.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Author : TrungNguyen
 * Date   : 4/2/2026
 * Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
//@Builder
public class Doctor extends Person{

    private String speciality;

    public Doctor(String id, String name, String phone, String speciality) {
        super(id, name, phone);
        this.speciality = speciality;
    }

}
