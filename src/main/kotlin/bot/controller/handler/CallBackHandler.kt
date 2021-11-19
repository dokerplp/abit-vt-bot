package bot.controller.handler

import bot.model.controller.FaqController
import bot.model.controller.LanguageController
import bot.model.controller.LinkController
import bot.model.entity.FaqEntity
import bot.model.entity.LinkEntity
import bot.utli.enums.Language
import bot.utli.getChatId
import bot.utli.sendMessage
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod
import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton

@Component("callBackHandler")
class CallBackHandler(
    @Autowired val textHandler: TextHandler,
    @Autowired val faqController: FaqController,
    @Autowired val languageController: LanguageController
) : Handler {
    override fun handle(update: Update): Array<PartialBotApiMethod<Message>>? {
        val text = update.callbackQuery.data
        return if (text.startsWith("link")) linkCallBack(text, update)
        else textHandler.handle(text, update)
    }

    private fun linkCallBack(text: String, update: Update): Array<PartialBotApiMethod<Message>>? {

        val id = text.substring(5).toLong()
        val faq: FaqEntity = faqController.getById(id)

        var response = ""
        if (languageController.getById(getChatId(update)).language == Language.EN) {
            response = faq.answer.en
        }
        else if (languageController.getById(getChatId(update)).language == Language.RU) {
            response = faq.answer.ru
        }

        val links = faq.links

        val msg = sendMessage(update)
        msg.text = response

        val buttons: MutableList<List<InlineKeyboardButton>> = ArrayList()
        if (links != null) {
            for (link in links) {
                val button = InlineKeyboardButton()
                button.text = link.text
                button.url = link.value
                val bl: MutableList<InlineKeyboardButton> = ArrayList()
                bl.add(button)
                buttons.add(bl)
            }
        }
        val keyboard = InlineKeyboardMarkup(buttons)
        msg.replyMarkup = keyboard

        return arrayOf(msg)
    }
}