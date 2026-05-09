package iuh.fit.dao;

import iuh.fit.model.Doctor;
import iuh.fit.util.AppUtil;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.neo4j.driver.summary.ResultSummary;
import org.neo4j.driver.types.Node;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Author : TrungNguyen
 * Date   : 4/1/2026
 * Description:
 */
public class DoctorDao {
    public static Doctor findDoctoyById (String doctorID){
        String query =
                """
                    MATCH(d:Doctor)
                    WHERE d.doctor_id = $doctorID
                    return d
                """;

        Map<String, Object> params = Map.of("doctorID", doctorID);
        try(Session session = AppUtil.getSession()){
            return session.executeRead(tx-> {
                Result result = tx.run(query, params);
                if(result.hasNext()){
                    Node node = result.next().get("d").asNode();

                    return Doctor
                            .builder()
                            .doctorId(node.get("doctor_id").asString())
                            .name(node.get("name").asString())
                            .phone(node.get("phone").asString())
                            .speciality(node.get("speciality").asString())
                            .deptId(node.get("dept_id").asString())
                            .build();
                }
                return null;
            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Map<String, Long> getNoOfDoctorsBySpeciality (String departmentName){
        String query =
                """
                    MATCH(d:Doctor)-[r:BELONG_TO]->(db:Department)
                    WHERE db.name = $departmentName
                    RETURN d.speciality AS Speciality, count(d) AS totalDoctor
                    ORDER BY totalDoctor DESC
                """;

        Map<String, Object> params = Map.of("departmentName", departmentName);
        try(Session session = AppUtil.getSession()){
            Result result = session.run(query, params);
                return result.stream().collect(
                        Collectors.toMap(
                                r -> r.get("Speciality").asString(),
                                r-> r.get("totalDoctor").asLong()
                        )
                );

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean addDoctor (Doctor doctor){

        String query =
                """
                    CREATE (d:Doctor)
                    SET d.doctor_id = $doctor_Id,
                        d.name = $name,
                        d.phone = $phone,
                        d.speciality = $speciality,
                        d.dept_id = $deptId
                    return d
                """;

        Map<String, Object> params = Map.of(
                "doctor_Id", doctor.getDoctorId(),
                "name", doctor.getName(),
                "phone", doctor.getPhone(),
                "speciality", doctor.getSpeciality(),
                "deptId", doctor.getDeptId());
        try(Session session = AppUtil.getSession()){
            return session.executeWrite(tx ->{
                ResultSummary resultSummary = tx.run(query, params).consume();
                return resultSummary.counters().nodesCreated() > 0;
            });

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Doctor> listDoctorsBySpeciality (String keyword){
        List<Doctor> listDoctor = new ArrayList<>();
        String query =
                """
                    CALL db.index.fulltext.queryNodes("specialityIndex", $keyword) YIELD node
                    RETURN node
                """;

        Map<String, Object> params = Map.of("keyword", keyword);

        try(Session session = AppUtil.getSession()){
            return session.executeRead(tx ->{
                Result result = tx.run(query, params);
                return result
                        .stream()
                        .map(r -> {
                            Node node = r.get("node").asNode();
                            return Doctor
                                    .builder()
                                    .doctorId(node.get("doctor_id").asString())
                                    .name(node.get("name").asString())
                                    .phone(node.get("phone").asString())
                                    .speciality(node.get("speciality").asString())
                                    .deptId(node.get("dept_id").asString())
                                    .build();
                        })
                        .toList();
            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean updateDiagnosis (String patientID, String doctorID, String newDiagnosis){

        String query =
                """
                    MATCH(p:Patient)-[r:BE_TREATED]->(d:Doctor)
                    WHERE p.patient_id =$patientID AND d.doctor_id = $doctorID AND r.endDate IS NULL
                    SET r.diagnosis = $newDiagnosis
                """;

        Map<String, Object> params = Map.of(
                "patientID", patientID,
                "doctorID", doctorID,
                "newDiagnosis", newDiagnosis);

        try(Session session = AppUtil.getSession()){
            return session.executeWrite(tx ->{
                ResultSummary resultSummary = tx.run(query, params).consume();
                return resultSummary.counters().propertiesSet() > 0;
            });

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
