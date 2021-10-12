package bot.run

import bot.commands.Command
import bot.commands.Invoker
import bot.commands.Receiver
import bot.util.BotUpdatesHandler
import bot.util.CommandsUtil.notifyAdmin
import bot.util.Log
import bot.util.ResourceOperator
import bot.util.database.DatabaseConnection
import bot.util.database.SqlFunctions
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.TelegramBotsApi
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.exceptions.TelegramApiException
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession

class AbitVTBot : TelegramLongPollingBot() {

    private val log = Log()

    private val updatesHandler: BotUpdatesHandler = BotUpdatesHandler(this)
    private val invoker: Invoker = Invoker(this)

    val sqlFunctions: SqlFunctions = SqlFunctions(DatabaseConnection().getConnection())
    val resourceOperator: ResourceOperator = ResourceOperator(this)

    init {
        Receiver(this).setCommands(invoker)
        log.turnOn()
        Runtime.getRuntime().addShutdownHook(Thread { log.turnOff() })
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val bot = AbitVTBot()
            val telegramBotsApi = TelegramBotsApi(DefaultBotSession::class.java)
            telegramBotsApi.registerBot(bot)
        }
    }

    override fun getBotToken(): String {
        return System.getenv("BOT_TOKEN")
    }

    override fun getBotUsername(): String {
        return System.getenv("BOT_NAME")
    }

    override fun onUpdateReceived(update: Update?) {
        try {
            updatesHandler.newUpdate(update!!)
        } catch (e: TelegramApiException) {
            log.telegramApiException(e, update!!)
        } catch (e: Exception) {
            log.appError(e, update!!)
            notifyAdmin(e, update)
        }
    }

    fun inv(command: String?, update: Update?) {
        invoker.execute(command, update!!)
    }

    fun availableCommands(): LinkedHashMap<String, Command> {
        return invoker.commandMap
    }
}