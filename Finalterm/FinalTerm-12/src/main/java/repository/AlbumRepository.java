package repository;

import entity.Album;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class AlbumRepository extends genericRepository<Album, String> {
    public boolean updatePriceOfAlbum(String id, double newPrice){
        return doInTransaction(em -> {
            Album album = em.find(Album.class, id);
            album.setPrice(newPrice);
            em.merge(album);
            return true;
        });
    }

    public List<Album> listAlbumByGenre(String genreName, int year){
        return doInTransaction(em -> {
            String jpql = """
                    select a
                    from Album a
                    where lower(a.genre.name) like lower(:genreName) and a.yearOfRelease = :year
                    """;

            return em.createQuery(jpql, Album.class).setParameter("genreName", "%" + genreName + "%").setParameter("year", year).getResultList();
        });
    }

    public Map<String, Long> getNumberOfAlbumsByGenre(){
        return doInTransaction(em -> {
            String jpql = """
                    select a.genre.name, count (a)
                    from Album a
                    group by a.genre.name
                    order by count(a) asc
                    """;

            return em.createQuery(jpql, Object[].class).getResultList().stream().collect(Collectors.toMap(
                    objects -> (String) objects[0],
                    objects -> (Long) objects[1]
            ));

        });
    }
}
