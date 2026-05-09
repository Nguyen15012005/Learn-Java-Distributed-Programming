package iuh.fit;

import iuh.fit.entity.Country;
import iuh.fit.util.JsonUtil;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Country country = JsonUtil.readerFileJson("json/country.json");
        System.out.println(country);
    }
}