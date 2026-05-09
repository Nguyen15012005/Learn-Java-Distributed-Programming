package iuh.fit.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Data
public class Address {
    private String street;
    private String city;
    private String state;
    private String zip;
}
