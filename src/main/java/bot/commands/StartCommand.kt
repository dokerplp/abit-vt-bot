package bot.commands

import bot.enums.Stickers
import bot.run.AbitVTBot
import bot.util.CommandsUtil.getChatId
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update

class StartCommand(private val bot : AbitVTBot) : Command {
    override fun execute(update: Update) {
        val chatId = getChatId(update)

        val sendMessage = SendMessage()
        sendMessage.chatId = chatId
        sendMessage.text = "Welcome to the club buddy!"

        Stickers.WELCOME_TO_THE_CLUB.sendSticker(bot, update)

        bot.execute(sendMessage)
    }

    override fun help(chatId: String): String {
        return "/start - welcome to the club buddy..."
    }

    override fun name(): String {
        return "/start"
    }
}