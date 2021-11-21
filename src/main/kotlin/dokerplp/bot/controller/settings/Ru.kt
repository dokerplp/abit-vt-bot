package dokerplp.bot.controller.settings

import dokerplp.bot.model.controller.LanguageController
import dokerplp.bot.model.entity.LanguageEntity
import dokerplp.bot.utli.ResourceOperator
import dokerplp.bot.utli.enums.Language
import dokerplp.bot.utli.getChatId
import dokerplp.bot.utli.sendMessage
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod
import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.api.objects.Update

@Component("ru")
class Ru(
    @Autowired val languageController: LanguageController,
    @Autowired val resourceOperator: ResourceOperator
) : Setting {

    override fun execute(update: Update): Array<PartialBotApiMethod<Message>>? {
        val chatId = getChatId(update)
        val msg = sendMessage(update)
        msg.chatId = chatId.toString()
        languageController.save(LanguageEntity(chatId, Language.RU))
        msg.text = resourceOperator.getText("language", update)
        return arrayOf(msg)
    }
}