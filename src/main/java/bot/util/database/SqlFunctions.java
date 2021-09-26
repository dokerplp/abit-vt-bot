package bot.util.database;

import bot.enums.Language;
import bot.util.Log;
import org.postgresql.util.PSQLException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SqlFunctions {

    private final Log log = new Log();

    private final Connection connection;

    public SqlFunctions(Connection connection) {
        this.connection = connection;
    }

    public boolean insertLanguage(String chatId, Language language){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SqlScripts.INSERT_LANGUAGE);

            preparedStatement.setLong(1, Long.parseLong(chatId));
            preparedStatement.setString(2, language.toString());

            preparedStatement.execute();

            return true;
        } catch (SQLException throwables) {
            log.userException(throwables);
            return false;
        }
    }

    public boolean updateLanguage(String chatId, Language language){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SqlScripts.UPDATE_LANGUAGE);

            preparedStatement.setLong(2, Long.parseLong(chatId));
            preparedStatement.setString(1, language.toString());

            preparedStatement.executeUpdate();

            return true;
        } catch (SQLException throwables) {
            log.userException(throwables);
            return false;
        }
    }

    public Language selectLanguage(String chatId){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SqlScripts.SELECT_LANGUAGE);

            preparedStatement.setLong(1, Long.parseLong(chatId));

            ResultSet resultSet = preparedStatement.executeQuery();

            try {
                resultSet.next();
                String language = resultSet.getString("language");
                return Language.convert(language);
            } catch (PSQLException e){
                boolean insert = insertLanguage(chatId, Language.EN);
                if (insert){
                    return Language.EN;
                }
                else return null;
            }

        } catch (SQLException throwables) {
            log.userException(throwables);
            return null;
        }
    }
}
