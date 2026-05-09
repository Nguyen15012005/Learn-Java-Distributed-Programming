package iuh.fit.dao;

import iuh.fit.model.Product;
import iuh.fit.util.AppUtil;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.neo4j.driver.summary.ResultSummary;
import org.neo4j.driver.types.Node;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Author : TrungNguyen
 * Date   : 4/3/2026
 * Description:
 */
public class ProductDao {
//    Với các điều kiện sau:
//            − companyName: Không được null hoặc rỗng
//− page: Phải là số nguyên dương (page ≥ 1)
//− size: Phải là số nguyên dương (size ≥ 1)
//− Nếu không hợp lệ → thông báo lỗi hoặc ném exception

    public static List<Product> listProductsBySupplier(String companyName, int page, int size){
        if (companyName == null)
            throw new IllegalArgumentException("companyName khong duoc de trong");
        if (page < 1 || size < 1)
            throw new IllegalArgumentException("Page hoac Size phai la so nguyen");

        int skip = (page - 1)* size;
        String query =
                """
                    MATCH(s:Supplier)-[r:SUPPLIES]->(p:Product)
                    WHERE s.company_name = $companyName
                    RETURN p 
                    SKIP $skip 
                    LIMIT $size
                """;

        Map<String, Object> params = Map.of(
                "companyName",companyName,
                "skip",skip,
                "size",size
        );

        try(Session session = AppUtil.getSession()){
            return session.executeRead(tx -> {
                Result result = tx.run(query,params);
                return result.stream().map(
                        record -> {
                            Node node = record.get("p").asNode();
                            return new Product(
                                    node.get("product_id").asString(),
                                    node.get("product_name").asString(),
                                    node.get("uni t").asString(),
                                    node.get("unit_price").asDouble(),
                                    node.get("units_in_stock").asInt()
                            );
                        }
                ).toList();
            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Product> listProductsByName(String name, int page, int size){
        if (name == null)
            throw new IllegalArgumentException("name khong duoc rong");
        if (page < 1 || size < 1)
            throw new IllegalArgumentException("page va size lon hon hoac bang 1");
        int skip = (page - 1)*size;
        String query =
                """
                    MATCH(p:Product)
                    WHERE p.product_name STARTS WITH $name
                    RETURN p
                    ORDER BY p.unit_price SKIP $skip LIMIT $size
                """;

        Map<String, Object> params = Map.of(
            "name", name,
            "skip", skip,
            "size", size
        );

        try(Session session = AppUtil.getSession()){
            return session.executeRead(tx -> {
               Result result = tx.run(query,params);
               return result
                       .stream()
                       .map(record -> {
                           Node node = record.get("p").asNode();
                           return new Product(
                                   node.get("product_id").asString(),
                                   node.get("product_name").asString(),
                                   node.get("unit").asString(),
                                   node.get("unit_price").asDouble(),
                                   node.get("units_in_stock").asInt()

                           );
                       })
                       .toList();
            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public static boolean addProduct(Product product, String supplierID){

        String query =
                """
                    CREATE(p:Product)
                    SET p.product_id = $product_id, 
                        p.product_name = $product_name, 
                        p.unit = $unit, 
                        p.unit_price = $unit_price, 
                        p.units_in_stock = $units_in_stock
                    WITH p
                    MATCH(s:Supplier {supplier_id:$supplierID})
                    MERGE(p)-[:SUPPLIERS]->(s)
                """;

        Map<String, Object> params = Map.of(
                "product_id",product.getProductID(),
                "product_name",product.getProductName(),
                "unit",product.getUnit(),
                "unit_price",product.getUnitPrice(),
                "units_in_stock",product.getUnitslnStock(),
                "supplierID",supplierID
        );

        try(Session session = AppUtil.getSession()){
            return session.executeWrite(tx -> {
                ResultSummary resultSummary = tx.run(query, params).consume();
                return resultSummary.counters().nodesCreated() > 0;
            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public static Map<String, Long> countProductsBySupplier(){
        String query =
                """
                    MATCH(p:Product)-[r:SUPPLIERS]->(s:Supplier)
                    RETURN s.company_name AS Name, count(p) AS totalSupplier
                """;

        Map<String, Object> params = Map.of();

        try (Session session = AppUtil.getSession()){
            return session.executeRead(tx ->{
               Result result = tx.run(query);
                return result.stream().collect(Collectors.toMap(
                        r-> r.get("Name").asString(),
                        r-> r.get("totalSupplier").asLong()
                ));
            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Map<String, Long> countOrdersByStatus(){
        String query =
                """
                    MATCH(p:Product)-[r:SUPPLIERS]->(s:Supplier)
                    RETURN s.company_name AS Name, count(p) AS totalSupplier
                """;

        Map<String, Object> params = Map.of();

        try (Session session = AppUtil.getSession()){
            return session.executeRead(tx ->{
                Result result = tx.run(query);
                return result.stream().collect(Collectors.toMap(
                        r-> r.get("Name").asString(),
                        r-> r.get("totalSupplier").asLong()
                ));
            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



    static void main() {
        ProductDao.countProductsBySupplier().forEach((k,v)->{
            System.out.println(k + ":" + v);
        });
    }
}
