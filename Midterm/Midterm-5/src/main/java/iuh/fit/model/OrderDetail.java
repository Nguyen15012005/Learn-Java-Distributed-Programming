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
public class OrderDetail {
    private Order order;
    private Product product;
    private int quantity;
    private Double unitPrice;
    private Double discount;
}
