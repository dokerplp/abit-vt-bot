package bot.util.settings

import bot.run.AbitVTBot
import bot.util.CommandsUtil.getChatId
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow


class Settings(private val bot: AbitVTBot) : Setting {

    override fun execute(update: Update?) {
        val chatId = getChatId(update!!)
        val sendMessage = SendMessage()
        sendMessage.chatId = chatId
        sendMessage.text = "List of available languages:"
        val keyboardMarkup = ReplyKeyboardMarkup()
        val keyboard: MutableList<KeyboardRow> = ArrayList()
        val row = KeyboardRow()
        keyboardMarkup.resizeKeyboard = true
        row.add("⚙️ Ru \uD83C\uDDF7\uD83C\uDDFA")
        row.add("⚙️ En \uD83C\uDDFA\uD83C\uDDF8")
        keyboard.add(row)
        keyboardMarkup.keyboard = keyboard
        sendMessage.replyMarkup = keyboardMarkup
        bot.execute(sendMessage)
    }
}