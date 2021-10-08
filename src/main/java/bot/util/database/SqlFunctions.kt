package bot.util.database

import bot.enums.Language
import bot.util.Log
import org.postgresql.util.PSQLException
import java.sql.Connection
import java.sql.SQLException

class SqlFunctions(private val connection: Connection?) {
    private val log = Log()


    fun insertLanguage(chatId: String, language: Language): Boolean {
        return try {
            val preparedStatement = connection!!.prepareStatement(SqlScripts.INSERT_LANGUAGE)
            preparedStatement.setLong(1, chatId.toLong())
            preparedStatement.setString(2, language.toString())
            preparedStatement.execute()
            true
        } catch (throwables: SQLException) {
            log.userException(throwables)
            false
        }
    }

    fun updateLanguage(chatId: String, language: Language): Boolean {
        return try {
            val preparedStatement = connection!!.prepareStatement(SqlScripts.UPDATE_LANGUAGE)
            preparedStatement.setLong(2, chatId.toLong())
            preparedStatement.setString(1, language.toString())
            preparedStatement.executeUpdate()
            true
        } catch (throwables: SQLException) {
            log.userException(throwables)
            false
        }
    }

    fun selectLanguage(chatId: String): Language? {
        return try {
            val preparedStatement = connection!!.prepareStatement(SqlScripts.SELECT_LANGUAGE)
            preparedStatement.setLong(1, chatId.toLong())
            val resultSet = preparedStatement.executeQuery()
            try {
                resultSet.next()
                val language = resultSet.getString("language")
                Language.convert(language)
            } catch (e: PSQLException) {
                val insert = insertLanguage(chatId, Language.EN)
                if (insert) {
                    Language.EN
                } else null
            }
        } catch (throwables: SQLException) {
            log.userException(throwables)
            null
        }
    }
}