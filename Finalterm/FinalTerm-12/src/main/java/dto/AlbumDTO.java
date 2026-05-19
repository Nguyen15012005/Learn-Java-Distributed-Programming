package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AlbumDTO implements Serializable {
    private String id;

    private String title;

    private double price;

    private int yearOfRelease;

    private String genreName;

    private String downloadLink;


}
