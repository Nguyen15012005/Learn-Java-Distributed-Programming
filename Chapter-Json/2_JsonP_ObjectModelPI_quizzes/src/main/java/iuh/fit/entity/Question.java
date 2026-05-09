package iuh.fit.entity;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
@ToString
public class Question {
    private String question_id;
    private String text;
    private List<String> options;
    private String correct_answer;
}
