package bot.util.database;

import bot.enums.Language;
import org.postgresql.util.PSQLException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SqlFunctions {

    private final Connection connection;

    public SqlFunctions(Connection connection) {
        this.connection = connection;
    }

    public boolean insertLanguage(Long chatId, Language language){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SqlScripts.INSERT_LANGUAGE);

            preparedStatement.setLong(1, chatId);
            preparedStatement.setString(2, language.toString());

            preparedStatement.execute();

            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    public Language selectLanguage(Long chatId){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SqlScripts.SELECT_LANGUAGE);

            preparedStatement.setLong(1, chatId);

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
            throwables.printStackTrace();
            return null;
        }
    }
}
