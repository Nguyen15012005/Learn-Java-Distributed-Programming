package iuh.fit.model;

import iuh.fit.enums.Status;
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
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Order {
    private String orderID;
    private LocalDate orderDate;
    private String customerName;
    private String employeeName;
    private Status status;
}
