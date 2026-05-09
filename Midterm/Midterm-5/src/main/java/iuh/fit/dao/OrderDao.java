package iuh.fit.dao;

import iuh.fit.enums.Status;
import iuh.fit.model.Order;
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
public class OrderDao {
//    public static double calculateTotalOrder(String orderID){
//
//        String query =
//                """
//                        MATCH(o:Order)-[r:ORDERS]->(p:Product)
//                        WHERE o.order_id = $orderID
//                        RETURN sum(r.quantity*r.unit_price*(1 - r.discount)) AS totalPrice
//                """;
//
//        Map<String, Object> params = Map.of(
//                "orderID", orderID
//        );
//
//        try(Session session = AppUtil.getSession()){
//            return session.executeRead(tx -> {
//                Result result = tx.run(query,params);
//                return result.next().get("totalPrice").asDouble();
//            });
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//
//    }

    public static List<Order> listOrdersByStatus(String status){
        String query =
                """
                    MATCH(o:Order)
                    WHERE o.status = $status
                    RETURN o
                """;
        Map<String, Object> params = Map.of("status", status);

        try (Session session = AppUtil.getSession()){
            return session.executeRead(tx -> {
               Result result = tx.run(query, params);
               return result
                       .stream()
                       .map(record -> {
                           Node node = record.get("o").asNode();
                           return new Order(
                                   node.get("order_id").asString(),
                                   node.get("order_date").asLocalDate(),
                                   node.get("customer_name").asString(),
                                   node.get("employee_name").asString(),
                                   Status.valueOf(node.get("status").asString())
                           );
                       })
                       .toList();
            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean updateOrderStatus(String orderID, String status){
        String query =
                """
                    MATCH (o:Order)
                    WHERE o.order_id = $orderID
                    SET o.status = $status
                """;

        Map<String, Object> params = Map.of(
                "orderID",orderID,
                "status",status
        );

        try (Session session = AppUtil.getSession()){
            return session.executeWrite(tx -> {
                ResultSummary resultSummary = tx.run(query, params).consume();
                return resultSummary.counters().propertiesSet() > 0;
            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public static double calculateTotalOrder(String orderID){
        String query =
                """
                    MATCH(o:Order)-[r:ORDERS]->(p:Product)
                    WHERE o.order_id = $orderID
                    RETURN toFloat(sum(r.quantity*r.unit_price*(1-r.discount))) AS totalPrice
                """;

        Map<String, Object> params = Map.of(
                "orderID", orderID
        );

        try (Session session = AppUtil.getSession()){
            return session.executeRead(tx -> {
               Result result = tx.run(query, params);
               return result.next().get("totalPrice").asDouble();
            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Map<Integer, Double> revenueByMonth(int year){
        String query = "";
        Map<String, Object> params = Map.of();

    }


    static void main() {
        double totalOrder = calculateTotalOrder("O005");
        System.out.println(totalOrder);
    }
}
