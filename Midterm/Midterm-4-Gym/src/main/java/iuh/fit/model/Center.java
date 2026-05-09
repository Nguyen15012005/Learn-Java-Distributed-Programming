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
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Center {
    private String id;
    private String name;
    private String address;
}
