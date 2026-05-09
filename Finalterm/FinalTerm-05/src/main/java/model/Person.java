package model;

import jakarta.persistence.*;

/**
 * @author TrungNguyen
 * @created 4/22/2026
 * @description
 */

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "Discriminator")//dùng để phân biệt các class con trong kế thừa JPA khi dùng 1 bảng (SINGLE_TABLE).
public abstract class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PersonID")
    protected int id;

    @Column(name = "FirstName")
    protected String firstName;

    @Column(name = "LastName")
    protected String lastName;
}
