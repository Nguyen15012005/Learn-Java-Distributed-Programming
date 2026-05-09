package iuh.fit.entity;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
@ToString
public class Quiz {
    private String quiz_id;
    private String name;
    private int score;
    private List<Question> questions;
    private Category category;
}
