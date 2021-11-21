package dokerplp.bot.controller.handler

import dokerplp.bot.model.controller.FaqController
import dokerplp.bot.model.controller.LanguageController
import dokerplp.bot.model.controller.SubjectController
import dokerplp.bot.model.entity.FaqEntity
import dokerplp.bot.model.entity.SubjectEntity
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

@Component("callBackHandler")
class CallBackHandler(
    @Autowired val textHandler: TextHandler,
    @Autowired val faqController: FaqController,
    @Autowired val subjectController: SubjectController,
    @Autowired val languageController: LanguageController
) : Handler {

    /**
     * Trying to send answer on faq or subject callback data
     *
     * @param update
     * @return - array of SendMessage, SendSticker and so on
     */
    override fun handle(update: Update): Array<PartialBotApiMethod<Message>>? {
        val text = update.callbackQuery.data
        return if (text.startsWith("faq")) faqCallBack(text, update)
        else if (text.startsWith("sub")) subCallBack(text, update)
        else textHandler.handle(text, update)
    }

    /**
     * Faq callback handler
     *
     * @param text - faq: + id
     * @param update
     * @return - question answer
     */
    private fun faqCallBack(text: String, update: Update): Array<PartialBotApiMethod<Message>> {

        val id = text.substring(4).toLong()
        val faq: FaqEntity = faqController.getById(id)!!

        val response = when (languageController.getById(getChatId(update))!!.language) {
            Language.RU -> faq.answer.ru
            Language.EN -> faq.answer.en
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

    /**
     * Subject callback handler
     *
     * @param text - sub: + id
     * @param update
     * @return - subject description
     */
    private fun subCallBack(text: String, update: Update): Array<PartialBotApiMethod<Message>> {
        val id = text.substring(4).toLong()
        val sub: SubjectEntity = subjectController.getById(id)!!

        val response = when (languageController.getById(getChatId(update))!!.language) {
            Language.RU -> sub.description.ru
            Language.EN -> sub.description.en
        }

        val links = sub.links

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