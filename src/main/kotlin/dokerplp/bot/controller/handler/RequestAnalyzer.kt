package dokerplp.bot.controller.handler

import dokerplp.bot.model.controller.FaqController
import dokerplp.bot.model.controller.LanguageController
import dokerplp.bot.model.entity.FaqEntity
import dokerplp.bot.model.entity.QuestionEntity
import dokerplp.bot.utli.ResourceOperator
import dokerplp.bot.utli.enums.Language
import dokerplp.bot.utli.getChatId
import dokerplp.bot.utli.sendMessage
import dokerplp.bot.utli.stringSimilarity
import org.languagetool.JLanguageTool
import org.languagetool.language.BritishEnglish
import org.languagetool.language.Russian
import org.languagetool.tools.Tools.correctTextFromMatches
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

    /**
     * Trying to analyze message and suggest questions
     *
     * @param text - user message
     * @param update
     * @return - questions or invoke default in TextHandler
     */
    fun analyze(text: String, update: Update): Array<PartialBotApiMethod<Message>>? {

        val faqs = suggest(text)
        if (faqs.isEmpty()) return null
        else {
            val msg = sendMessage(update)
            msg.text = resourceOperator.getText("analyze.text", update)

            val buttons: MutableList<List<InlineKeyboardButton>> = ArrayList()
            for (faq in faqs) {
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
    }

    /**
     * Searching correct questions
     *
     * Algorithm:
     * If question and message have 3 identical words, question will be suggested
     * Word must have at least 3 chars
     *
     * @param text - user message
     * @return - suggested questions
     */
    private fun suggest(text: String): List<FaqEntity> {

        val en = JLanguageTool(BritishEnglish())
        val ru = JLanguageTool(Russian())

        val curText = correctText(correctText(text, en), ru).lowercase()

        val func: Function<QuestionEntity, Boolean> = Function {
            ((stringSimilarity(it.en, curText) > 0.5) or (stringSimilarity(it.ru, curText) > 0.5)) or
                    ((stringSimilarity(it.en, text) > 0.5) or (stringSimilarity(it.ru, text) > 0.5))
        }

        return faqController.findAll().toList().stream().filter { func.apply(it.question) }.toList()

    }

    /**
     * Automatically applies suggestions to the text, as suggested
     * by the rules that match.
     * Note: if there is more than one suggestion, always the first
     * one is applied, and others are ignored silently.
     *
     * @param contents String to be corrected
     * @param lt Initialized LanguageTool object
     * @return Corrected text as String.
     */
    private fun correctText(contents: String, lt: JLanguageTool): String {
        val ruleMatches = lt.check(contents)
        return if (ruleMatches.isEmpty()) {
            contents
        } else correctTextFromMatches(contents, ruleMatches)
    }
}