package bot.commands

import org.telegram.telegrambots.meta.api.objects.Update

interface Command {
    fun execute(update: Update)
    fun help(chatId: String): String
    fun name(): String
}