package iuh.fit.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

/**
 * Author : TrungNguyen
 * Date   : 4/2/2026
 * Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class Trainer extends Person{
    private String specialty;

    public Trainer(String id, String name, String phone, String specialty) {
        super(id, name, phone);
        this.specialty = specialty;
    }

}
