package iuh.fit.dao;

import iuh.fit.model.Trainer;
import iuh.fit.util.AppUtil;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.neo4j.driver.summary.ResultSummary;
import org.neo4j.driver.types.Node;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Author : TrungNguyen
 * Date   : 4/2/2026
 * Description:
 */
public class TrainerDao {
    public static Map<String, Long> getNoOfTrainersBySpecialty(String centerName){
        String query =
                """
                    MATCH(t:Trainer)-[r:BELONGS_TO]->(c:Center)
                    WHERE c.name = $centerName
                    WITH t.specialty AS Specialty, count(t) AS totalTrainer
                    RETURN Specialty, totalTrainer
                """;

        Map<String, Object> params = Map.of(
                "centerName", centerName
        );
        try(Session session = AppUtil.getSession()){
            return session.executeRead(tx -> {
                Result result = tx.run(query, params);
                return result.stream().collect(Collectors.toMap(
                        r -> r.get("Specialty").asString(),
                        r -> r.get("totalTrainer").asLong()
                ));
            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean addTrainerToCenter(Trainer trainer, String centerName){
        String query =
                """
                    CREATE(t:Trainer)
                    SET t.id = $id, t.name = $name, t.phone = $phone, t.specialty = $specialty
                    WITH t
                    MATCH(c:Center {name:$centerName})
                    MERGE(t)-[:BELONGS_TO]->(c)
                """;
//        MERGE(t:Trainer {id: $id})
//        SET t.name = $name, t.phone = $phone, t.specialty = $specialty
//        WITH t
//        MATCH(c:Center {name:$centerName})
//        MERGE(t)-[:BELONGS_TO]->(c)
        Map<String, Object> params = Map.of(
                "id", trainer.getId(),
                "name", trainer.getName(),
                "phone", trainer.getPhone(),
                "specialty", trainer.getSpecialty(),
                "centerName", centerName
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

    public static List<Trainer> listTrainersBySpecialty (String keyword){
        String query =
                """
                    CALL db.index.fulltext.queryNodes("indexTrainer", $keyword) YIELD node
                    RETURN node
                """;

        Map<String, Object> params = Map.of("keyword", keyword);
        try(Session session = AppUtil.getSession()){
            return session.executeRead(tx -> {
               Result result = tx.run(query, params);
               return result
                       .stream()
                       .map(record -> {
                           Node node = record.get("node").asNode();
                           Trainer trainer = new Trainer(
                                   node.get("id").asString(),
                                   node.get("name").asString(),
                                   node.get("phone").asString(),
                                   node.get("specialty").asString()
                           );
                           return trainer;
                       })
                       .toList();
            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean updateSessionDurationst(String memberID, String trainerID, Float newDuration){
        String query =
                """
                    MATCH(m:Member)-[r:TRAINED_BY]->(t:Trainer)
                    WHERE m.id = $memberID AND t.id = $trainerID
                    SET r.duration = $newDuration
                """;

        Map<String, Object> params = Map.of(
                "memberID", memberID,
                "trainerID", trainerID,
                "newDuration", newDuration
        );
        try(Session session = AppUtil.getSession()){
            return session.executeWrite(tx -> {
               ResultSummary resultSummary = tx.run(query, params).consume();
               return resultSummary.counters().propertiesSet() > 0;
            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
