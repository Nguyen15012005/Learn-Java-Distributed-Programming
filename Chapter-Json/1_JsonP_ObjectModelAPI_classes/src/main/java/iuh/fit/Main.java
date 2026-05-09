package iuh.fit;

import iuh.fit.model.ClassInfo;
import iuh.fit.util.JsonUtils;

import java.io.FileNotFoundException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        List<ClassInfo> res = JsonUtils.toJson("json/classes.json");
        res.forEach(System.out::println);

        JsonUtils.writeJsonToFile(res, "json/nguyen23640731.json");
    }
}