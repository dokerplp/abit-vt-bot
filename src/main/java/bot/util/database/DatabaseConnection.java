package bot.util.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

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
            return DriverManager.getConnection("jdbc:postgresql://" + HOST + ":" + PORT + "/" + NAME, USER, PASS);
        } catch (ClassNotFoundException | SQLException e) {
            return null;
        }
    }
}
