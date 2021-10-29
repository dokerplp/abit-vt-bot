package bot.controller.commands

import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod
import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.api.objects.Update

interface Command {
    fun execute(update: Update): ArrayList<PartialBotApiMethod<Message>>?
    fun help(chatId: String): String
    fun name(): String
}