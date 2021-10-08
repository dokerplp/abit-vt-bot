package bot.util.settings

import bot.enums.Language
import bot.run.AbitVTBot
import bot.util.CommandsUtil.getChatId
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update


class EN(private val bot: AbitVTBot) : Setting {

    override fun execute(update: Update?) {
        val chatId = getChatId(update!!)
        val sendMessage = SendMessage()
        sendMessage.chatId = chatId
        sendMessage.text = "Language was changed to english"
        bot.sqlFunctions.updateLanguage(chatId, Language.EN)
        bot.execute(sendMessage)
    }
}