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
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Movie {
    private String title;
    private int released;
    private String tagline;
}
