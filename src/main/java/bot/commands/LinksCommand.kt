package bot.commands

import bot.run.AbitVTBot
import org.telegram.telegrambots.meta.api.objects.Update
import java.util.*

class LinksCommand(private val bot : AbitVTBot) : Command {
    override fun execute(update: Update) {
        TODO("Not yet implemented")
    }

    override fun help(chatId: String): String {
        return bot.resourceOperator.getHelp("links", chatId)!!
    }

    override fun name(): String {
        return "/links"
    }
}