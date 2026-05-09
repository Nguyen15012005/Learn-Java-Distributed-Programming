package model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 * Author : TrungNguyen
 * Date   : 4/7/2026
 * Description:
 */

@Entity
@Table(name = "groups")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_id")
    private int id;
    @Column(columnDefinition = "VARCHAR(45)", nullable = false)
    private String name;

    @ManyToMany(mappedBy = "groups")
    private Set<User> users;
}
