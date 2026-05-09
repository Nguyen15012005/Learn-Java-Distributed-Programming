package iuh.fit.util;

import iuh.fit.entity.Country;
import iuh.fit.entity.Name;
import iuh.fit.entity.Translation;
import jakarta.json.*;
import jakarta.json.stream.JsonGenerator;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;
import java.util.stream.IntStream;

public class JsonUtil {

    public static List<String> JsonToList(JsonArray jsonArray){
        List<String> res = new ArrayList<>();
        IntStream.range(0, jsonArray.size())
                .forEach(x -> res.add(jsonArray.getString(x)));

        return res;
    }

    public static JsonArrayBuilder listToJson(List<String> list){
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        list.forEach(x -> arrayBuilder.add(x));
        return arrayBuilder;
    }
    public static Country readerFileJson(String fileName){
        Country country = new Country();
        try (JsonReader reader = Json.createReader(new FileReader(fileName))){
            JsonObject countryJsonObject = reader.readObject();
            int id = countryJsonObject.getInt("id");

            List<String> altSpellings = JsonToList(countryJsonObject.getJsonArray("altSpellings"));

            int area = countryJsonObject.getInt("area");

            List<String> borders = JsonToList(countryJsonObject.getJsonArray("borders"));

            List<String> callingCode = JsonToList(countryJsonObject.getJsonArray("callingCode"));

            String capital = countryJsonObject.getString("capital");
            String cca2 = countryJsonObject.getString("cca2");
            String cioc = countryJsonObject.getString("cioc");

            List<String> currency = JsonToList(countryJsonObject.getJsonArray("currency"));

            String demonym = countryJsonObject.getString("demonym");
            Boolean landLocked = countryJsonObject.getBoolean("landLocked");

            List<Double> latlng = new ArrayList<>();
            JsonArray latlngJsonArray = countryJsonObject.getJsonArray("latlng");
            IntStream.range(0, latlng.size())
                    .forEach(x -> latlng.add((double) latlngJsonArray.getInt(x)));

            JsonObject nameJsonObject = countryJsonObject.getJsonObject("name");
            Name name = new Name(
                    nameJsonObject.getString("common"),
                    nameJsonObject.getString("official")
            );

            String region = countryJsonObject.getString("region");
            String subregion = countryJsonObject.getString("subregion");
            Map<String, Translation> translations = new LinkedHashMap<>();
            JsonObject translationJsonObject = countryJsonObject.getJsonObject("translations");
            translationJsonObject.keySet().forEach(key -> {
                JsonObject translationObject = translationJsonObject.get(key).asJsonObject();
                Translation translation = new Translation(
                        translationObject.getString("common"),
                        translationObject.getString("official")
                );
                translations.put(key, translation);
            });
            return new Country(
                id, area, capital, cca2, cioc, demonym, landLocked, region, subregion, altSpellings, borders, callingCode, currency, latlng, name, translations
            );

        }
        catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    public static void writeJson(List<Country> countries, String fileName){
        Map<String, Object> config = new HashMap<>();
        config.put(JsonGenerator.PRETTY_PRINTING, true);
        JsonWriterFactory jsonWriterFactory = Json.createWriterFactory(config);

        try(JsonWriter writer = jsonWriterFactory.createWriter(new FileWriter(fileName))){
            Country country = new Country();
            JsonObjectBuilder countryJsonObject = Json.createObjectBuilder()
                    .add("id", country.getId());
            JsonArrayBuilder altSpellings = listToJson(country.getAltSpellings()).add("altSpellings");
            countryJsonObject.add("altSpellings", altSpellings);
            countryJsonObject.add("area", country.getArea());
            JsonArrayBuilder borders = listToJson(country.getBorders()).add("borders");
            countryJsonObject.add("borders", borders);
            JsonArrayBuilder callingCode = listToJson(country.getCallingCode()).add("callingCode");
            countryJsonObject.add("callingCode", callingCode);

            countryJsonObject.add("capital", country.getCapital());
            countryJsonObject.add("cca2", country.getCca2());
            countryJsonObject.add("cioc", country.getCioc());


            countryJsonObject.add("area", country.getArea());
            countryJsonObject.add("area", country.getArea());


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


















