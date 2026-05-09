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
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Supplier {
    private String supplierID;
    private String companyName;
    private String contactName;
    private String country;
}
