package infrastructure.repository.impl;

import core.entity.Album;
import infrastructure.config.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Map;

public class AlbumRepositoryImpl {
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

    public List<Album> listAlbumByGenre(String genreName, int year){
        try(EntityManager em = JPAUtil.getEntityManager()){
            String jpql = """
                    select a
                    from Album a
                    where a.genre.name = :genreName and a.yearOfRelease = :year
                    """;

            TypedQuery<Album> query = em.createQuery(jpql, Album.class);
            query.setParameter("genreName", genreName);
            query.setParameter("year", year);
            return query.getResultList();
        }
    }

    public Map<String, Long> getNumberOfAlbumsByGenre(){
        try(EntityManager em = JPAUtil.getEntityManager()){

        }
    }
    //Test
    public static void main(String[] args) {
        AlbumRepositoryImpl albumRepository = new AlbumRepositoryImpl();
        System.out.println(albumRepository.updatePriceOfAlbum("ALB001", 40.5) ? "Cập nhập thành công" : "Cập nhâp thất bại");

        albumRepository.listAlbumByGenre("Blues", 1967).forEach(System.out::println);
//        Blues 1967
    }
}
