package iuh.fit.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Author : TrungNguyen
 * Date   : 4/1/2026
 * Description:
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Doctor {
    private String doctorId;
    private String name;
    private String phone;
    private String speciality;
    private String deptId;
}
