package bot.commands

import bot.run.AbitVTBot
import org.telegram.telegrambots.meta.api.objects.Update

class AuthorCommand(private val bot : AbitVTBot) : Command {
    override fun execute(update: Update) {
        TODO("Not yet implemented")
    }

    override fun help(chatId: String): String {
        return bot.resourceOperator.getHelp("author", chatId)!!
    }

    override fun name(): String {
        return "/author"
    }
}