package bot.util.database

import bot.util.Log
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

class DatabaseConnection {
    private val log = Log()

    private var HOST: String? = System.getenv("HOST")
    private var USER: String? = System.getenv("USER")
    private var PASS: String? = System.getenv("PASS")
    private var NAME: String? = System.getenv("NAME")

    fun getConnection(): Connection? {
        return try {
            Class.forName("org.postgresql.Driver")
            val URL = "jdbc:postgresql://$HOST:5432/$NAME"
            log.databaseConnect(URL, PASS, USER)
            DriverManager.getConnection(URL, USER, PASS)
        } catch (e: ClassNotFoundException) {
            log.userException(e)
            null
        } catch (e: SQLException) {
            log.userException(e)
            null
        }
    }
}