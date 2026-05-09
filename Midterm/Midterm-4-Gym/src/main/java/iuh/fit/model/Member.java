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
@AllArgsConstructor
@NoArgsConstructor
@Data
//@Builder
public class Member extends Person {
    private LocalDate joinDate;
}
