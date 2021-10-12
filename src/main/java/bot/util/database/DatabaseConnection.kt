package bot.util.database

import bot.util.Log
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException
import kotlin.system.exitProcess

class DatabaseConnection {
    private val log = Log()

    private var HOST: String? = System.getenv("HOST")
    private var USER: String? = System.getenv("USER")
    private var PASS: String? = System.getenv("PASS")
    private var NAME: String? = System.getenv("NAME")

    fun getConnection(): Connection {
        val URL = "jdbc:postgresql://$HOST:5432/$NAME"
        return try {
            Class.forName("org.postgresql.Driver")
            log.databaseConnect(URL, PASS, USER)
            DriverManager.getConnection(URL, USER, PASS)
        } catch (e: ClassNotFoundException) {
            log.userException(e)
            log.databaseConnectionError(URL, USER, PASS)
            exitProcess(1)
        } catch (e: SQLException) {
            log.userException(e)
            log.databaseConnectionError(URL, USER, PASS)
            exitProcess(1)
        }
    }
}