package iuh.fit.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Data
public class Student {
    private String name;
    private int age;
    private double gpa;
    private Address address;
}
