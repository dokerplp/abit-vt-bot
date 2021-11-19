package bot.controller.commands

import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod
import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.api.objects.Update

interface Command {
    /**
     * Executes command
     *
     * @param update
     * @return array of SendMessage, SendSticker and so on
     */
    fun execute(update: Update): Array<PartialBotApiMethod<Message>>?

    /**
     * For help command
     *
     * @param update
     * @return text for help command
     */
    fun help(update: Update): String

    /**
     * Name of command
     *
     * @return
     */
    fun name(): String
}