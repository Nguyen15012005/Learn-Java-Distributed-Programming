package iuh.fit.dao;

import iuh.fit.model.Doctor;
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
 * Date   : 4/2/2026
 * Description:
 */
public class DoctorDao {
    public static boolean addDoctor(Doctor doctor){
        String query =
                """
                    CREATE (d:Doctor) 
                    SET d.id = $id, d.name = $name, d.phone = $phone, d.specialty = $specialty    
                    RETURN d  
                """;

        Map<String, Object> params = Map.of(
                "id", doctor.getId(),
                "name", doctor.getName(),
                "phone", doctor.getPhone(),
                "specialty", doctor.getSpeciality()
        );

        try(Session session = AppUtil.getSession()) {
            return session.executeWrite(tx -> {
                ResultSummary resultSummary = tx.run(query, params).consume();
                return resultSummary.counters().nodesCreated() > 0;
            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Map<String, Long> getNoOfDoctorsBySpectiality(String departmentName){
        String query =
                """
                    MATCH(d:Doctor)-[r:BELONG_TO]->(dp:Department)
                    WHERE dp.name = $departmentName
                    WITH d.specialty AS Specialty, count(d) as totalDoctor
                    RETURN Specialty , totalDoctor  
                """;

        Map<String, Object> params = Map.of(
                "departmentName", departmentName
        );

        try(Session session = AppUtil.getSession()) {
            return session.executeRead(tx -> {
               Result result = tx.run(query, params);
               return result
                       .stream()
                       .collect(Collectors.toMap(
                               r -> r.get("Specialty").asString(),
                               r -> r.get("totalDoctor").asLong()
                       ));
            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Doctor> listDoctorBySpeciality(String keyword){
        String query =
                """
                    CALL db.index.fulltext.queryNodes("indexSpecialty", $keyword) YIELD node
                    RETURN node
                """;

        Map<String, Object> params = Map.of(
                "keyword", keyword
        );

        try(Session session = AppUtil.getSession()) {
            return session.executeRead(tx-> {
                Result result = tx.run(query, params);
                return result
                        .stream()
                        .map(
                                record -> {
                                    Node node = record.get("node").asNode();
                                    Doctor doctor = new Doctor(
                                        node.get("id").asString(),
                                            node.get("name").asString(),
                                            node.get("phone").asString(),
                                            node.get("specialty").asString()
                                    );
                                    return doctor;
                                }
                        )
                        .toList();
            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean updateDiagnosis(String patientID, String doctorID, String newDiagnosis){
        String query =
                """
                   MATCH (d:Doctor)-[r:BE_TREATED]->(p:Patient)
                    WHERE p.id = $patientID AND d.id = $doctorID
                    SET r.diagnosis = $newDiagnosis
                """;

        Map<String, Object> params = Map.of(
                "patientID", patientID,
                "doctorID", doctorID,
                "newDiagnosis", newDiagnosis
        );

        try(Session session = AppUtil.getSession()) {
            return session.executeWrite(tx -> {
                ResultSummary resultSummary = tx.run(query, params).consume();
                return resultSummary.counters().propertiesSet() > 0;
            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
