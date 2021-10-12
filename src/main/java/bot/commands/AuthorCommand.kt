package bot.commands

import bot.run.AbitVTBot
import bot.util.CommandsUtil.getChatId
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update

class AuthorCommand(private val bot : AbitVTBot) : Command {
    override fun execute(update: Update) {
        val chatId = getChatId(update)

        val sendMessage = SendMessage()
        sendMessage.enableMarkdown(true)
        sendMessage.chatId = chatId
        sendMessage.text = bot.resourceOperator.getText("author.greeting", chatId)!!

        bot.execute(sendMessage)
    }

    override fun help(chatId: String): String {
        return bot.resourceOperator.getHelp("author", chatId)!!
    }

    override fun name(): String {
        return "/author"
    }
}