package iuh.fit.dao;

import iuh.fit.model.Movie;
import iuh.fit.model.Person;
import iuh.fit.util.AppUtil;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.neo4j.driver.summary.ResultSummary;
import org.neo4j.driver.types.Node;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Author : TrungNguyen
 * Date   : 4/1/2026
 * Description:
 */
public class MovieDao {

    public static List<Movie> listMoviesContainsName (String name){
        String query =
                """
                    MATCH(m:Movie)
                    WHERE m.title CONTAINS $name
                    RETURN m
                """;

        Map<String, Object> params = Map.of("name", name);
        try (Session session = AppUtil.getSession()){
            return session.executeRead(tx -> {
                Result result = tx.run(query, params);
                return result
                        .stream()
                        .map(
                        r->{
                            Node node = r.get("m").asNode();
                                return Movie
                                        .builder()
                                        .title(node.get("title").asString())
                                        .released(node.get("released").asInt())
                                        .tagline(node.get("tagline").asString())
                                        .build();
                        }
                        )
                        .toList();
            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean createMovie(Movie movie){
        String query =
                """
                    Create (m:Movie)
                    SET m.title = $title, 
                        m.released = $year,
                        m.tagline = $tagline
                        return m
                """;

        Map<String, Object> params = Map.of(
                "title", movie.getTitle(),
                "year", movie.getReleased(),
                "tagline", movie.getTagline()
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

    public static List<Movie> listMoviesByActorAndReleased (String actorName, int released){
        String query =
                """
                    MATCH(p:Person)-[r:ACTED_IN]->(m:Movie)
                    WHERE p.name = $actorName AND m.released = $released
                    RETURN m
                """;

        Map<String, Object> params = Map.of("actorName", actorName, "released", released);
        try (Session session = AppUtil.getSession()){
            return session.executeRead(tx -> {
                Result result = tx.run(query, params);
                return result
                        .stream()
                        .map(record -> {
                            Node node = record.get("m").asNode();
                            return Movie
                                    .builder()
                                    .title(node.get("title").asString())
                                    .released(node.get("released").asInt())
                                    .tagline(node.get("tagline").asString())
                                    .build();
                        })
                        .toList();
            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Map<String, Long> getNoOfMoviePerActorByReleasedRange(int from, int to){
        String query =
                """
                    MATCH(p:Person)-[ACTED_IN]->(m:Movie)
                    WHERE m.released > $from AND m.released < $to
                    WITH count(m) AS totalMovie, p.name AS Name
                    RETURN Name, totalMovie
                """;
        Map<String, Object> params = Map.of(
                "from", from, "to", to
        );
        try(Session session = AppUtil.getSession()){
            return session.executeRead(tx -> {
                Result result = tx.run(query, params);
                return result.stream().collect(Collectors.toMap(
                                record -> record.get("Name").asString(),
                                record -> record.get("totalMovie").asLong()
                        )
                );
            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Person> listPersonByKeyword (String keyword){
        String query =
                """
                    CALL db.index.fulltext.queryNodes("personIndex", $keyword) YIELD node
                    RETURN node
                """;

        Map<String, Object> params = Map.of("keyword", keyword);
        try (Session session = AppUtil.getSession()){
            return session.executeRead(tx -> {
                Result result = tx.run(query, params);
                return result
                        .stream()
                        .map(
                                r->{
                                    Node node = r.get("node").asNode();
                                    return Person
                                            .builder()
                                            .name(node.get("name").asString())
                                            .born(node.get("born").asInt())
                                            .build();
                                }
                        )
                        .toList();
            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
