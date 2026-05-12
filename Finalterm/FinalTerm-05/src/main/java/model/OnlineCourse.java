package model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;

import java.io.Serializable;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@ToString(callSuper = true)
public class OnlineCourse extends Course implements Serializable {

    @Column(name = "URL")
    private String url;
}
