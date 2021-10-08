package bot.commands

import bot.run.AbitVTBot
import bot.util.CommandsUtil.getChatId
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.exceptions.TelegramApiException
import java.util.*

class Invoker(private val bot : AbitVTBot) {
    val commandMap = LinkedHashMap<String, Command>()

    fun add(key: String, command: Command) {
        commandMap[key] = command
    }

    fun execute(key: String?, update: Update) {
        val command = commandMap[key]
        command?.execute(update) ?: run {
            val chatId = getChatId(update)
            val message = SendMessage()
            message.chatId = update.message.chatId.toString()
            message.text = bot.resourceOperator.getText("invoker.text", chatId)!!
            bot.execute(message)
        }
    }
}