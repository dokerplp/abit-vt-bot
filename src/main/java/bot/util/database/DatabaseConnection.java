package bot.util.database;

import bot.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private final Log log = new Log();

    private final String HOST;
    private final String PORT;
    private final String USER;
    private final String PASS;
    private final String NAME;

    public DatabaseConnection() {
        this.HOST = System.getenv("HOST");
        this.PORT = System.getenv("PORT");
        this.USER = System.getenv("USER");
        this.PASS = System.getenv("PASS");
        this.NAME = System.getenv("NAME");
    }

    public Connection getConnection(){
        try {
            Class.forName("org.postgresql.Driver");
            String URL = "jdbc:postgresql://" + HOST + ":" + PORT + "/" + NAME;
            log.databaseConnect(URL, PASS, USER);
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (ClassNotFoundException | SQLException e) {
            log.userException(e);
            return null;
        }
    }
}
