package bot.commands

import bot.run.AbitVTBot
import bot.util.CommandsUtil.getChatId
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow
import java.util.*

class SettingsCommand(private val bot : AbitVTBot) : Command {
    override fun execute(update: Update) {
        val chatId = getChatId(update)

        val sendMessage = SendMessage()
        sendMessage.chatId = chatId
        sendMessage.text = bot.resourceOperator.getText("settings.text", chatId)!!

        val keyboardMarkup = ReplyKeyboardMarkup()
        val keyboard: MutableList<KeyboardRow> = ArrayList()
        val row = KeyboardRow()

        keyboardMarkup.resizeKeyboard = true
        row.add(bot.resourceOperator.getText("settings.settings", chatId)!!)

        keyboard.add(row)
        keyboardMarkup.keyboard = keyboard
        sendMessage.replyMarkup = keyboardMarkup

        bot.execute(sendMessage)
    }

    override fun help(chatId: String): String {
        return bot.resourceOperator.getHelp("settings", chatId)!!
    }

    override fun name(): String {
        return "/settings"
    }
}