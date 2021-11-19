package bot.controller.settings

import bot.model.controller.LanguageController
import bot.model.entity.LanguageEntity
import bot.utli.ResourceOperator
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
    @Autowired val languageController: LanguageController,
    @Autowired val resourceOperator: ResourceOperator
) : Setting {

    override fun execute(update: Update): Array<PartialBotApiMethod<Message>>? {
        val chatId = getChatId(update)
        val msg = sendMessage(update)
        msg.chatId = chatId.toString()
        languageController.save(LanguageEntity(chatId, Language.EN))
        msg.text = resourceOperator.getText("language", update)
        return arrayOf(msg)
    }
}