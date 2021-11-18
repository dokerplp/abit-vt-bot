package bot.controller.settings

import bot.model.controller.LanguageController
import bot.model.entity.LanguageEntity
import bot.utli.enums.Language
import bot.utli.getChatId
import bot.utli.sendMessage
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod
import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.api.objects.Update

@Component("ru")
class Ru(
    @Autowired val languageController: LanguageController
) : Setting {

    override fun execute(update: Update): Array<PartialBotApiMethod<Message>>? {
        val chatId = getChatId(update)
        val msg = sendMessage(update)
        msg.chatId = chatId.toString()
        msg.text = "Язык был сменен на русский"
        languageController.save(LanguageEntity(chatId, Language.RU))
        return arrayOf(msg)
    }
}