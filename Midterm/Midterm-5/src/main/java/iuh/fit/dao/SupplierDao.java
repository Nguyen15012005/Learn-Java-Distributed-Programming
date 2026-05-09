package iuh.fit.dao;

import iuh.fit.model.Supplier;
import iuh.fit.util.AppUtil;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.neo4j.driver.summary.ResultSummary;
import org.neo4j.driver.types.Node;

import java.util.List;
import java.util.Map;

/**
 * Author : TrungNguyen
 * Date   : 4/3/2026
 * Description:
 */
public class SupplierDao {
    public static boolean updateSupplier(Supplier supplier){

        if (supplier ==  null)
            throw new IllegalArgumentException("supplier khong duoc null");
        if (supplier.getSupplierID() ==  null || supplier.getSupplierID().isEmpty())
            throw new IllegalArgumentException("supplierID khong duoc null hoac rong");
        if (supplier.getCompanyName() == null)
            throw new IllegalArgumentException("supplierID khong duoc null hoac rong");

        String query =
                """
                    MATCH(s:Supplier)
                    WHERE s.supplier_id = $supplier_id
                    SET s.company_name = $company_name, s.contact_name = $contact_name, s.country = $country
                """;

        Map<String, Object> params = Map.of(
                "supplier_id", supplier.getSupplierID(),
                "company_name", supplier.getCompanyName(),
                "contact_name", supplier.getContactName(),
                "country", supplier.getCountry()
        );

        try(Session session = AppUtil.getSession()){
            return session.executeWrite(tx ->{
                ResultSummary resultSummary = tx.run(query, params).consume();
                return resultSummary.counters().propertiesSet() > 0;
            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    public static List<Supplier> listSuppliersByCountry(String country){
        String query =
                """
                    MATCH(s:Supplier)
                    WHERE s.country = $country
                    RETURN s
                """;
        Map<String, Object> params = Map.of(
                "country", country
        );

        try (Session session = AppUtil.getSession()){
            return session.executeRead(tx -> {
                Result result = tx.run(query, params);
                return result
                        .stream()
                        .map(record -> {
                            Node node = record.get("s").asNode();
                            return new Supplier(
                                    node.get("supplier_id").asString(),
                                    node.get("company_name").asString(),
                                    node.get("contact_name").asString(),
                                    node.get("country").asString()
                            );
                        })
                        .toList();
            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
