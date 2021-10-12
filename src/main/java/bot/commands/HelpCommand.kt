package bot.commands

import bot.run.AbitVTBot
import bot.util.CommandsUtil.getChatId
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton
import java.util.*

class HelpCommand(private val bot : AbitVTBot) : Command {
    override fun execute(update: Update) {
        val chatId = getChatId(update)

        val sendMessage = SendMessage()
        sendMessage.chatId = chatId
        sendMessage.text = bot.resourceOperator.getText("help.text", chatId)!!

        val buttons: MutableList<List<InlineKeyboardButton>> = ArrayList()

        for (command in bot.availableCommands().values) {
            val button = InlineKeyboardButton()
            button.text = command.help(chatId)
            button.callbackData = command.name()
            val bl: MutableList<InlineKeyboardButton> = ArrayList()
            bl.add(button)
            buttons.add(bl)
        }

        val keyboard = InlineKeyboardMarkup(buttons)

        sendMessage.replyMarkup = keyboard

        bot.execute(sendMessage)
    }

    override fun help(chatId: String): String {
        return bot.resourceOperator.getHelp("help", chatId)!!
    }

    override fun name(): String {
        return "/help"
    }
}