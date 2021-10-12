package bot.commands

import bot.run.AbitVTBot
import org.telegram.telegrambots.meta.api.objects.Update

class SubjectsCommand(private val bot : AbitVTBot) : Command {
    override fun execute(update: Update) {
    }

    override fun help(chatId: String): String {
        return bot.resourceOperator.getHelp("subjects", chatId)!!
    }

    override fun name(): String {
        return "/subjects"
    }
}