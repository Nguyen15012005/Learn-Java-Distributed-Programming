package infrastructure.repository;

import core.entity.Album;

import java.util.List;
import java.util.Map;

public interface AlbumRepository {
    boolean updatePriceOfAlbum(String id, double newPrice);

    List<Album> listAlbumByGenre(String genreName, int year);

    Map<String, Long> getNumberOfAlbumsByGenre();
}
