package iuh.fit.dao;

import iuh.fit.util.AppUtil;
import org.neo4j.driver.Session;
import org.neo4j.driver.summary.ResultSummary;

import java.util.Map;

/**
 * Author : TrungNguyen
 * Date   : 4/3/2026
 * Description:
 */
public class OrderDetailDao {
    public static boolean addOrderDetail(String orderID, String productID, int quantity, double price, double discount){
        String query =
                """
                    MATCH(o:Order {order_id:$orderID})
                    MATCH(p:Product {product_id:$productID})
                    WITH o, p
                    MERGE (o)-[r:ORDERS]->(p)
                    SET r.discount = $discount, r.quantity = $quantity, r.unit_price = $price
                """;

        Map<String, Object> params = Map.of(
                "orderID",orderID,
                "productID",productID,
                "discount",discount,
                "quantity",quantity,
                "price",price
        );

        try (Session session = AppUtil.getSession()){
            return session.executeWrite(tx-> {
                ResultSummary resultSummary = tx.run(query, params).consume();
                return resultSummary.counters().nodesCreated() > 0;
            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void test(){
        String query =
                """
                """;

        Map<String, Object> params = Map.of();

        try (Session session = AppUtil.getSession()){

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
