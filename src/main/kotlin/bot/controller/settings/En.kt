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

@Component("en")
class En(
    @Autowired val languageController: LanguageController
) : Setting {

    override fun execute(update: Update): Array<PartialBotApiMethod<Message>>? {
        val chatId = getChatId(update)
        val msg = sendMessage(update)
        msg.chatId = chatId.toString()
        msg.text = "Language was changed to english"
        languageController.save(LanguageEntity(chatId, Language.EN))
        return arrayOf(msg)
    }
}