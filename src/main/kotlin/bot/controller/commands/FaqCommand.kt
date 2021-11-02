package bot.controller.commands

import bot.model.controller.LanguageController
import bot.utli.ResourceOperator
import bot.utli.getChatId
import bot.utli.sendMessage
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod
import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.api.objects.Update

@Component("/faq")
class FaqCommand(@Autowired val languageController: LanguageController) : Command {

    @Autowired
    val resourceOperator: ResourceOperator? = null

    override fun execute(update: Update): Array<PartialBotApiMethod<Message>>? {
        TODO()
    }

    override fun help(update: Update): String {
        return resourceOperator!!.getHelp("faq", getChatId(update))!!
    }

    override fun name(): String {
        return "/faq"
    }
}