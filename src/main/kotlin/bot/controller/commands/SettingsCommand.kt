package bot.controller.commands

import bot.utli.sendMessage
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod
import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow
import java.util.ArrayList

@Component("/settings")
class SettingsCommand : Command{
    override fun execute(update: Update): Array<PartialBotApiMethod<Message>>? {
        val msg = sendMessage(update)
        msg.text = "settings"

        val keyboardMarkup = ReplyKeyboardMarkup()
        val keyboard: MutableList<KeyboardRow> = ArrayList()
        val row = KeyboardRow()

        keyboardMarkup.resizeKeyboard = true
        row.add("settings")

        keyboard.add(row)
        keyboardMarkup.keyboard = keyboard
        msg.replyMarkup = keyboardMarkup

        return arrayOf(msg)
    }


    override fun help(update: Update): String {
        return "settings"
    }

    override fun name(): String {
        return "/settings"
    }
}