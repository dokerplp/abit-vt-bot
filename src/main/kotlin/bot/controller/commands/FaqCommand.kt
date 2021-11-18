package bot.controller.commands

import bot.model.controller.FaqController
import bot.model.controller.LanguageController
import bot.utli.ResourceOperator
import bot.utli.getChatId
import bot.utli.sendMessage
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
        msg.text = resourceOperator.getText("faq.text", getChatId(update))!!

        val buttons: MutableList<List<InlineKeyboardButton>> = ArrayList()
        for (faq in faqController.findAll()) {
            val button = InlineKeyboardButton()
            button.text = faq.question.ru
            button.callbackData = faq.answer.ru
            val bl: MutableList<InlineKeyboardButton> = ArrayList()
            bl.add(button)
            buttons.add(bl)
        }
        val keyboard = InlineKeyboardMarkup(buttons)
        msg.replyMarkup = keyboard

        return arrayOf(msg)
    }

    //TODO("if findall is empty exception throws")

    override fun help(update: Update): String {
        return resourceOperator.getHelp("faq", getChatId(update))!!
    }

    override fun name(): String {
        return "/faq"
    }
}