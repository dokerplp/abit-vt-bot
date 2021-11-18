package bot.controller.commands

import bot.utli.ResourceOperator
import bot.utli.getChatId
import bot.utli.sendMessage
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod
import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow

@Component("/settings")
class SettingsCommand(
    @Autowired val resourceOperator: ResourceOperator
) : Command {


    override fun execute(update: Update): Array<PartialBotApiMethod<Message>>? {
        val msg = sendMessage(update)
        msg.text = resourceOperator.getText("settings.text", getChatId(update))!!

        val keyboardMarkup = ReplyKeyboardMarkup()
        val keyboard: MutableList<KeyboardRow> = ArrayList()
        val row = KeyboardRow()

        keyboardMarkup.resizeKeyboard = true
        row.add(resourceOperator.getText("settings.settings", getChatId(update))!!)

        keyboard.add(row)
        keyboardMarkup.keyboard = keyboard
        msg.replyMarkup = keyboardMarkup

        return arrayOf(msg)
    }


    override fun help(update: Update): String {
        return resourceOperator.getHelp("settings", getChatId(update))!!
    }

    override fun name(): String {
        return "/settings"
    }
}