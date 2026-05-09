import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import model.Group;
import model.User;

/**
 * Author : TrungNguyen
 * Date   : 4/7/2026
 * Description:
 */
public class Main {
    public static void main(String[] args) {
        EntityManager em = Persistence.createEntityManagerFactory("mariadb").createEntityManager();

        User user = new User();
        user.setUsername("NguyenNamTrungNguyen");
        user.setPassword("11092020");
        user.setEmail("nguyennamtrungnguyen@gmail.com");

        Group group = new Group();
        group.setName("Software Engineer");

//        Note kiến thức cần lưu ý:
//          Có 2 loại trong 1 quan hệ
//              Người quản lý mối quan hệ (Owning side) (Nó quan tâm mối quan hệ)
//              Người ánh xạ qua (Inverse side) (Chỉ ánh xạ, không quan tâm gì hết)

//      Từ user thêm vào group => thằng user nó sẽ quan tâm tới mối quan hệ
        user.getGroups().add(group);

//      Tu group thêm vào user => thằng này nó chỉ có quan tâm nó thêm vào được hay không chứ không quan tâm mối quan hệ
//        group.getUsers().add(user);

        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
    }
}
