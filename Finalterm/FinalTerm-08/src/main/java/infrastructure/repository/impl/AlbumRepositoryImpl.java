package infrastructure.repository.impl;

import core.entity.Album;
import infrastructure.config.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class AlbumRepositoryImpl implements infrastructure.repository.AlbumRepository {
    @Override
    public boolean updatePriceOfAlbum(String id, double newPrice){
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            String jpql = """
                   update Album a
                   set a.price = :newPrice
                   where a.id = :id
                   """;

            int updated = em.createQuery(jpql).setParameter("id", id).setParameter("newPrice", newPrice).executeUpdate();
            tx.commit();
            return updated > 0;
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
            return false;
        }
        finally {
            em.close();
        }
    }

    @Override
    public List<Album> listAlbumByGenre(String genreName, int year){
        try(EntityManager em = JPAUtil.getEntityManager()){
            String jpql = """
                    select a
                    from Album a
                    where lower(a.genre.name) like :genreName and a.yearOfRelease = :year
                    """;

            TypedQuery<Album> query = em.createQuery(jpql, Album.class);
            query.setParameter("genreName","%" + genreName.toLowerCase() + "%");
            query.setParameter("year", year);
            return query.getResultList();
        }
    }

    @Override
    public Map<String, Long> getNumberOfAlbumsByGenre(){
        try(EntityManager em = JPAUtil.getEntityManager()){
            String jpql = """
                    select a.genre.name, count (a)
                    from Album a
                    group by a.genre.name
                    order by a.genre.name asc
                    """;

            TypedQuery<Object[]> query = em.createQuery(jpql, Object[].class);

            return query.getResultList()
                    .stream()
                    .collect(Collectors
                            .toMap(
                                    objects -> (String) objects[0],
                                    objects -> (Long) objects[1],
                                    (o1, o2) -> o1,
                                    LinkedHashMap::new
                            )
                    );
        }
    }
    //Test
//    public static void main(String[] args) {
//        AlbumRepositoryImpl albumRepository = new AlbumRepositoryImpl();
//        System.out.println(albumRepository.updatePriceOfAlbum("ALB001", 40.5) ? "Cập nhập thành công" : "Cập nhâp thất bại");
//
//        albumRepository.listAlbumByGenre("Blues", 1967).forEach(System.out::println);
//
//        albumRepository.getNumberOfAlbumsByGenre().forEach((k, v) -> System.out.println(k + "|" + v));
//    }
}
