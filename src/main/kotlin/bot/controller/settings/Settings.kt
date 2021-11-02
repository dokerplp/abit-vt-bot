package bot.controller.settings

import bot.utli.sendMessage
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod
import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow

@Component
class Settings : Setting {
    override fun execute(update: Update): Array<PartialBotApiMethod<Message>>? {
        val msg = sendMessage(update)
        msg.text = "List of available languages:"
        val keyboardMarkup = ReplyKeyboardMarkup()
        val keyboard: MutableList<KeyboardRow> = ArrayList()
        val row = KeyboardRow()
        keyboardMarkup.resizeKeyboard = true
        row.add("⚙️ Ru \uD83C\uDDF7\uD83C\uDDFA")
        row.add("⚙️ En \uD83C\uDDFA\uD83C\uDDF8")
        keyboard.add(row)
        keyboardMarkup.keyboard = keyboard
        msg.replyMarkup = keyboardMarkup
        return arrayOf(msg)
    }
}