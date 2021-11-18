package bot.controller.commands

import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod
import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.api.objects.Update

interface Command {
    fun execute(update: Update): Array<PartialBotApiMethod<Message>>?
    fun help(update: Update): String
    fun name(): String
}