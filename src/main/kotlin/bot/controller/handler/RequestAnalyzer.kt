package bot.controller.handler

import bot.model.controller.FaqController
import bot.model.controller.LanguageController
import bot.model.entity.FaqEntity
import bot.model.entity.QuestionEntity
import bot.utli.ResourceOperator
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
import java.util.function.Function
import kotlin.streams.toList


@Component
class RequestAnalyzer(
    @Autowired val faqController: FaqController,
    @Autowired val resourceOperator: ResourceOperator,
    @Autowired val languageController: LanguageController
    ) {

    fun analyze(text: String, update: Update): Array<PartialBotApiMethod<Message>>? {

        val faqs = suggest(text)
        if (faqs.isEmpty()) return null

        else {
            val msg = sendMessage(update)
            msg.text = resourceOperator.getText("analyze.text", getChatId(update))!!

            val buttons: MutableList<List<InlineKeyboardButton>> = ArrayList()
            for (faq in faqs) {
                val button = InlineKeyboardButton()
                when ((languageController.getById(getChatId(update)).language)) {
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
    }


    private fun suggest(text: String): List<FaqEntity> {

        val textArr: List<String> = text.split(" ").stream().map { it.lowercase() }.filter { it.length > 3 }.toList()

        val func: Function<QuestionEntity, Long> = Function {
            textArr.stream().filter { el -> it.en.lowercase().contains(el.lowercase()) or it.ru.lowercase().contains(el.lowercase()) }.count()
        }

        return faqController.findAll().toList().stream().filter { func.apply(it.question) > 2 }.toList()

    }
}