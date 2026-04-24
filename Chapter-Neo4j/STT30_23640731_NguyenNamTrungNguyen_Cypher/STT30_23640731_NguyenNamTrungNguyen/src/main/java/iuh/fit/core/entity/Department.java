package iuh.fit.core.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Department {

    private String id;
    private String name;
    private String building;
    private String room;
    private String dean;
}