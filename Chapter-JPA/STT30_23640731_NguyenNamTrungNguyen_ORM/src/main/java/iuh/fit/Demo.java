package iuh.fit;

import iuh.fit.db.Neo4jSessionFactory;
import iuh.fit.entity.Department;
import org.neo4j.ogm.transaction.Transaction;
import org.neo4j.ogm.session.Session;

import java.util.Collection;

public class Demo {
    public static void main(String[] args) {

        Session session = null;
        Transaction tx = null;

        try {
            session = Neo4jSessionFactory.openSession();
            tx = session.beginTransaction();
            // 👉 THÊM DỮ LIỆU (đúng entity của bạn)
            Department d1 = Department.builder()
                    .id("EE")
                    .name("Dien Dien Tu")
                    .dean("Nguyen Van A")
                    .building("A")
                    .room("101")
                    .build();

            Department d2 = Department.builder()
                    .id("IT")
                    .name("Cong Nghe Thong Tin")
                    .dean("Tran Van B")
                    .building("B")
                    .room("202")
                    .build();

            Department d3 = Department.builder()
                    .id("BA")
                    .name("Quan Tri Kinh Doanh")
                    .dean("Le Thi C")
                    .building("C")
                    .room("303")
                    .build();

            session.save(d1);
            session.save(d2);
            session.save(d3);



            // sửa lỗi id:
            Department department = session.load(Department.class, "EE");

            // load all + in ra
            session.loadAll(Department.class)
                    .forEach(System.out::println);

            // in 1 department
            System.out.println(department);

            tx.commit();

        } catch (Exception e) {

        } finally {
            if (session != null) {
                session.clear();
            }
        }
    }
}