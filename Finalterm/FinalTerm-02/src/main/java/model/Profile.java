package model;

import jakarta.persistence.*;
import lombok.*;

/**
 * Author : TrungNguyen
 * Date   : 4/7/2026
 * Description:
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "profiles")
public class Profile {
    @Id
    @EqualsAndHashCode.Include
    private Long id;
    private String avatar;

    @Column(columnDefinition = "TEXT")
    private String description;

    @OneToOne
    @JoinColumn(name = "staff_id")
    @MapsId
    @ToString.Exclude
    private Staff staff;
}
