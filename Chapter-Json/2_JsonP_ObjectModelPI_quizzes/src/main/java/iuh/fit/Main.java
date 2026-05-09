package iuh.fit;

import iuh.fit.entity.Quiz;
import iuh.fit.util.QuizUtil;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Quiz> res = QuizUtil.listQuizzes("C001");
        res.forEach(System.out::println);

        QuizUtil.writeJsonToFile(res,"data/nguyen23640731.json");
    }
}