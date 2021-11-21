package dokerplp.bot.controller.commands

import dokerplp.bot.model.controller.FaqController
import dokerplp.bot.model.controller.LanguageController
import dokerplp.bot.utli.ResourceOperator
import dokerplp.bot.utli.enums.Language
import dokerplp.bot.utli.getChatId
import dokerplp.bot.utli.sendMessage
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod
import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton

@Component("/faq")
class FaqCommand(
    @Autowired val languageController: LanguageController,
    @Autowired val resourceOperator: ResourceOperator,
    @Autowired val faqController: FaqController
) : Command {

    override fun execute(update: Update): Array<PartialBotApiMethod<Message>>? {
        val msg = sendMessage(update)
        msg.text = resourceOperator.getText("faq.text", update)

        val buttons: MutableList<List<InlineKeyboardButton>> = ArrayList()
        for (faq in faqController.findAll()) {
            val button = InlineKeyboardButton()
            when ((languageController.getById(getChatId(update))!!.language)) {
                Language.EN -> button.text = faq.question.en
                Language.RU -> button.text = faq.question.ru
            }
            button.callbackData = "faq:" + faq.id
            val bl: MutableList<InlineKeyboardButton> = ArrayList()
            bl.add(button)
            buttons.add(bl)
        }
        val keyboard = InlineKeyboardMarkup(buttons)
        msg.replyMarkup = keyboard

        return arrayOf(msg)
    }

    override fun help(update: Update): String {
        return resourceOperator.getHelp("faq", update)
    }

    override fun name(): String {
        return "/faq"
    }
}