package bot.util.database;

public class SqlScripts {
    public static final String SELECT_LANGUAGE = "SELECT language FROM settings where id = ?;";

    public static final String INSERT_LANGUAGE = "INSERT INTO settings VALUES (?, ?::\"lang\");";

    public static final String UPDATE_LANGUAGE = "UPDATE settings SET language=?::\"lang\" WHERE id=?;";
}
