package bot.util.settings

import bot.enums.Language
import bot.run.AbitVTBot
import bot.util.CommandsUtil.getChatId
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.exceptions.TelegramApiException

class RU(private val bot : AbitVTBot) : Setting {

    override fun execute(update: Update?) {
        val chatId = getChatId(update!!)
        val sendMessage = SendMessage()
        sendMessage.chatId = chatId
        sendMessage.text = "Язык был сменен на русский"
        bot.sqlFunctions.updateLanguage(chatId, Language.RU)
        bot.execute(sendMessage)
    }
}