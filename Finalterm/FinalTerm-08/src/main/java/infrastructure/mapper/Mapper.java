package infrastructure.mapper;

import core.dto.AlbumDTO;
import core.entity.Album;

public class Mapper {

    public static AlbumDTO toDTO (Album album){
        return new AlbumDTO(
                album.getId(), album.getTitle(), album.getPrice(), album.getYearOfRelease(), album.getDownloadLink(), album.getGenre().getId()
        );
    }
}
