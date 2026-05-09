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
public class Product {
    private String productID;
    private String productName;
    private String unit;
    private double unitPrice;
    private int unitslnStock;
    private Supplier supplier;

    public Product(String productID, String productName, String unit, double unitPrice, int unitslnStock) {
        this.productID = productID;
        this.productName = productName;
        this.unit = unit;
        this.unitPrice = unitPrice;
        this.unitslnStock = unitslnStock;
    }
}
