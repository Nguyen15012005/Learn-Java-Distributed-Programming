package iuh.fit.util;

import org.neo4j.driver.*;

/**
 * Author : TrungNguyen
 * Date   : 4/1/2026
 * Description:
 */
public class AppUtil {

    private static final String DB_Name = "midtern-01";
    private static final String UserName = "neo4j";
    private static final String PASSWORD = "11092020";
    private static final String URI = "neo4j://127.0.0.1:7687";

    private static Driver driver;

    public static Driver getDriver(){
        if(driver == null){
            driver = GraphDatabase.driver(URI, AuthTokens.basic(UserName, PASSWORD));
        }
        return driver;
    }

    public static Session getSession(){
        return getDriver().session(SessionConfig.forDatabase(DB_Name));
    }
}
