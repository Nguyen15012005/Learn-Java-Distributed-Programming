package iuh.fit.util;

import iuh.fit.entity.Category;
import iuh.fit.entity.Question;
import iuh.fit.entity.Quiz;
import jakarta.json.*;
import jakarta.json.stream.JsonGenerator;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;

public class QuizUtil {

    public static List<Quiz> listQuizzes(String categoryId){
        List<Quiz> res = new ArrayList<>();
        try(JsonReader reader = Json.createReader(new FileReader("data/quizzes.json"))){
            JsonArray quizJsonArray = reader.readArray();
            quizJsonArray.forEach(quizJsonValue ->{
                List<Question> questions = new ArrayList<>();

                JsonObject quizJsonObject = quizJsonValue.asJsonObject();
                String quiz_id = quizJsonObject.getString("quiz_id");
                String name = quizJsonObject.getString("name");
                int score = quizJsonObject.getInt("score");

                JsonArray questionJsonArray = quizJsonObject.getJsonArray("questions");
                questionJsonArray.forEach(questionJsonValue ->{

                    List<String> options = new ArrayList<>();
                    JsonObject questionJsonObject = questionJsonValue.asJsonObject();
                    String question_id = questionJsonObject.getString("question_id");
                    String text = questionJsonObject.getString("text");
                    JsonArray option = questionJsonObject.getJsonArray("options");
                    option.forEach(optionValue ->{
                        options.add(((JsonString) optionValue).getString());
                    });
                    String correct_answer = questionJsonObject.getString("correct_answer");
                    Question question = new Question(question_id, text, options, correct_answer);
                    questions.add(question);
                });
                JsonObject categoryJsonObject = quizJsonObject.getJsonObject("category");
                String category_id = categoryJsonObject.getString("category_id");
                String nameCategory = categoryJsonObject.getString("name");
                Category category = new Category(category_id, nameCategory);
                if (category_id.equals(categoryId)){
                    Quiz quiz = new Quiz(quiz_id, name,score,questions, category);
                    res.add(quiz);
                }
            });

        }
        catch (Exception e){
            e.printStackTrace();
        }
    return res;
    }

    public static void writeJsonToFile(List<Quiz> quizzes, String fileName){
        Map<String, Object> config = new HashMap<>();
        config.put(JsonGenerator.PRETTY_PRINTING, true);
        JsonWriterFactory jsonWriterFactory = Json.createWriterFactory(config);
        try (JsonWriter writer = jsonWriterFactory.createWriter(new FileWriter(fileName))){
            JsonArrayBuilder quizJsonArray = Json.createArrayBuilder();
            quizzes.forEach(x -> {
                JsonObjectBuilder quizJsonObject = Json.createObjectBuilder()
                        .add("quiz_id", x.getQuiz_id())
                        .add("name", x.getName())
                        .add("score", x.getScore());
                JsonArrayBuilder questionJsonArray = Json.createArrayBuilder();
                x.getQuestions().forEach(y -> {
                    JsonArrayBuilder optionArray = Json.createArrayBuilder();
                    y.getOptions().forEach(optionArray::add);
                    JsonObjectBuilder questionJsonObject = Json.createObjectBuilder()
                            .add("question_id", y.getQuestion_id())
                            .add("text", y.getText())
                            .add("options", optionArray)
                            .add("correct_answer", y.getCorrect_answer());
                    questionJsonArray.add(questionJsonObject);
                });
                JsonObjectBuilder categoryJsonObject = Json.createObjectBuilder()
                        .add("category_id", x.getCategory().getCategory_id())
                        .add("name", x.getCategory().getName());

                quizJsonObject.add("questions",questionJsonArray);
                quizJsonObject.add("category", categoryJsonObject);
                quizJsonArray.add(quizJsonObject);
            });
            writer.writeArray(quizJsonArray.build());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
