package iuh.fit.util;

import org.neo4j.driver.*;

/**
 * Author : TrungNguyen
 * Date   : 4/3/2026
 * Description:
 */
public class AppUtil {
    private static final String DB_NAME = "northwind";
    private static final String USERNAME = "neo4j";
    private static final String PASSWORD = "sapassword";
    private static final String URI = "neo4j://127.0.0.1:7687";

    public static Driver driver;

    public static Driver getDriver(){
        if(driver == null)
            driver = GraphDatabase.driver(URI, AuthTokens.basic(USERNAME, PASSWORD));
        return driver;
    }

    public static Session getSession(){
        return getDriver().session(SessionConfig.forDatabase(DB_NAME));
    }
}
