package iuh.fit.entity;

import lombok.*;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
@ToString
public class Country {
    private int id;
    private List<String> altSpellings;
    private int area;
    private List<String> borders;
    private List<String> callingCode;
    private String capital;
    private String cca2;
    private String cioc;
    private List<String> currency;
    private String demonym;
    private Boolean landLocked;
    private List<Double> latlng;
    private Name name;
    private String region;
    private String subregion;
    private Map<String, Translation> translations;

    public Country(int id, int area, String capital, String cca2, String cioc, String demonym, Boolean landLocked, String region, String subregion, List<String> altSpellings, List<String> borders, List<String> callingCode, List<String> currency, List<Double> latlng, Name name, Map<String, Translation> translations) {
    }
}
