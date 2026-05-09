package iuh.fit.model;

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
public class Department {
    private String id;
    private String name;
    private String location;
}
